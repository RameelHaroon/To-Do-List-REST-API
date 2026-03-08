package com.rameel.To_Do_List_API.service;

import com.rameel.To_Do_List_API.model.TaskCreateDTO;
import com.rameel.To_Do_List_API.model.TaskResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    public List<TaskResponseDTO> getTasksByUser(UUID userId);
    TaskResponseDTO createTask (UUID UserId, TaskCreateDTO dto);
}
