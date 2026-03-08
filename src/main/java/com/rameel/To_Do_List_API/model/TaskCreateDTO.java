package com.rameel.To_Do_List_API.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDTO {
    private String title;
    private String description;
}