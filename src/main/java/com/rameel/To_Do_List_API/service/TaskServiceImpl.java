package com.rameel.To_Do_List_API.service;

import com.rameel.To_Do_List_API.entity.Task;
import com.rameel.To_Do_List_API.entity.User;
import com.rameel.To_Do_List_API.mapper.TaskMapper;
import com.rameel.To_Do_List_API.model.TaskCreateDTO;
import com.rameel.To_Do_List_API.model.TaskResponseDTO;
import com.rameel.To_Do_List_API.reporsitory.TaskReposirtory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskReposirtory taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponseDTO> getTasksByUser(UUID userId) {
        return taskRepository.findByUserId(userId).stream().map(taskMapper::taskToTaskResponseDto).toList();
    }

    @Override
    public TaskResponseDTO createTask(UUID UserId,TaskCreateDTO dto) {
        Optional<User> user = userService.getUser(UserId);
        if(user.isPresent()){
            Task task = Task.builder()
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .status("To Do")
                    .user(user.get())
                    .build();
            Task saved = taskRepository.save(task);
            return taskMapper.taskToTaskResponseDto(saved);
        }
        return null;
    }
}
