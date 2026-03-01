package com.rameel.To_Do_List_API.service;

import com.rameel.To_Do_List_API.entity.User;
import com.rameel.To_Do_List_API.model.UserCreateDTO;
import com.rameel.To_Do_List_API.model.UserRegistrationResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserRegistrationResponseDTO registerUser(UserCreateDTO user);

    String login(User user);
}
