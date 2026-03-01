package com.rameel.To_Do_List_API.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponseDTO {
    private String name;
    private String email;
}
