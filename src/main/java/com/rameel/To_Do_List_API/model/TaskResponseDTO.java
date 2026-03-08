package com.rameel.To_Do_List_API.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private String title;
    private String description;
    private String status;
}
