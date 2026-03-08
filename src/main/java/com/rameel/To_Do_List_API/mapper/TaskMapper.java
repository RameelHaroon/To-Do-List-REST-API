package com.rameel.To_Do_List_API.mapper;

import com.rameel.To_Do_List_API.entity.Task;
import com.rameel.To_Do_List_API.model.TaskCreateDTO;
import com.rameel.To_Do_List_API.model.TaskResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDTO taskToTaskResponseDto(Task task);
}
