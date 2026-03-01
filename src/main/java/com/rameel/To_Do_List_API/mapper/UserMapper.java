package com.rameel.To_Do_List_API.mapper;

import com.rameel.To_Do_List_API.entity.User;
import com.rameel.To_Do_List_API.model.UserCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserCreateDTO user);
}
