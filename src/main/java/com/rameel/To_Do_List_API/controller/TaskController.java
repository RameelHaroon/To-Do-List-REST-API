package com.rameel.To_Do_List_API.controller;

import com.rameel.To_Do_List_API.model.TaskCreateDTO;
import com.rameel.To_Do_List_API.model.TaskResponseDTO;
import com.rameel.To_Do_List_API.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(TaskController.BASE_URL)
public class TaskController {

    public static final String BASE_URL = "api/taskService";
    public static final String V1_TASK = "/v1/task";
    public static final String V1_CUSTOMER_ID = V1_TASK + "/{customerId}";

    private final TaskService taskService;

    @GetMapping(V1_TASK)
    public List<TaskResponseDTO> getTasks(Authentication authentication) {
        UUID userId = UUID.fromString(Objects.requireNonNull(authentication.getPrincipal()).toString());
        return taskService.getTasksByUser(userId);
    }

    @PostMapping(V1_TASK)
    public ResponseEntity createTask(@RequestBody TaskCreateDTO dto, Authentication authentication){
        UUID userId = UUID.fromString(Objects.requireNonNull(authentication.getPrincipal()).toString());
        TaskResponseDTO savedTask = taskService.createTask(userId, dto);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}