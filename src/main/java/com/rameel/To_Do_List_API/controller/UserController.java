package com.rameel.To_Do_List_API.controller;

import com.rameel.To_Do_List_API.model.UserCreateDTO;
import com.rameel.To_Do_List_API.model.UserLoginDTO;
import com.rameel.To_Do_List_API.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "api/userService";
    public static final String V1_REGISTER_USER = "/v1/register";
    public static final String V1_LOGIN_USER = "/v1/login";

    private final UserService userService;

    @PostMapping(V1_REGISTER_USER)
    public ResponseEntity registerUser(@RequestBody UserCreateDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }

    @PostMapping(V1_LOGIN_USER)
    public ResponseEntity login(@RequestBody UserLoginDTO user) {
        return ResponseEntity.ok(userService.login(user));
    }
}
