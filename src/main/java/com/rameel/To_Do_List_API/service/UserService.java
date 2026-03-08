package com.rameel.To_Do_List_API.service;

import com.rameel.To_Do_List_API.entity.User;
import com.rameel.To_Do_List_API.model.UserCreateDTO;
import com.rameel.To_Do_List_API.model.UserLoginDTO;
import com.rameel.To_Do_List_API.model.UserRegistrationResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    UserRegistrationResponseDTO registerUser(UserCreateDTO user);

    String login(UserLoginDTO user);
    Optional<User> getUser(UUID uuid);
}
