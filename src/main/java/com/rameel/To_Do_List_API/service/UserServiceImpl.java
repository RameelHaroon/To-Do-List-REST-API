package com.rameel.To_Do_List_API.service;

import com.rameel.To_Do_List_API.mapper.UserMapper;
import com.rameel.To_Do_List_API.entity.User;
import com.rameel.To_Do_List_API.exception.EmailAlreadyExistsException;
import com.rameel.To_Do_List_API.model.UserCreateDTO;
import com.rameel.To_Do_List_API.model.UserRegistrationResponseDTO;
import com.rameel.To_Do_List_API.reporsitory.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
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
    public String login(User user) {
        return "";
    }
}
