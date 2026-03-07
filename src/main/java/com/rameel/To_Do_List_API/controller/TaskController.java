package com.rameel.To_Do_List_API.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(TaskController.BASE_URL)
public class TaskController {

    public static final String BASE_URL = "api/taskService";
    public static final String V1_TASK = "/v1/task";
    public static final String V1_CUSTOMER_ID = V1_TASK + "/{customerId}";

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping(V1_TASK)
    public String getTasks(Authentication authentication){
        return  (String) authentication.getPrincipal();
    }
}