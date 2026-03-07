package com.rameel.To_Do_List_API.service;

import com.rameel.To_Do_List_API.entity.User;
import com.rameel.To_Do_List_API.exception.EmailAlreadyExistsException;
import com.rameel.To_Do_List_API.exception.InvalidPassword;
import com.rameel.To_Do_List_API.exception.UnknownUser;
import com.rameel.To_Do_List_API.mapper.UserMapper;
import com.rameel.To_Do_List_API.model.UserCreateDTO;
import com.rameel.To_Do_List_API.model.UserLoginDTO;
import com.rameel.To_Do_List_API.model.UserRegistrationResponseDTO;
import com.rameel.To_Do_List_API.reporsitory.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RedisTemplate<String, String> redisTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserRegistrationResponseDTO registerUser(UserCreateDTO user) {

        userRepository.findByEmailIgnoreCase(user.getEmail())
                .ifPresent(u -> {
                    throw new EmailAlreadyExistsException(user.getEmail());
                });

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User newUser = this.userRepository.save(this.userMapper.userDtoToUser(user));

        UserRegistrationResponseDTO userResponse = UserRegistrationResponseDTO.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .build();

        return userResponse;
    }

    @Override
    public String login(UserLoginDTO user) {
        Optional<User> userDetails = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (userDetails.isEmpty()) {
            throw new UnknownUser(user.getEmail());
        }
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), userDetails.get().getPassword());
        if (!passwordMatches) {
            throw new InvalidPassword();
        }
        return generateAndStoreToken(String.valueOf(userDetails.get().getId()));
    }

    private String generateAndStoreToken(String id) {
        String token = UUID.randomUUID().toString();
        String key = "token:" + token;
        redisTemplate.opsForValue().set(key, id, 5, TimeUnit.MINUTES);
        return token;
    }
}
