package com.example.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinTableUserAndTaskDto {

    private String firstName;
    private String lastName;
    private String nameTask;
    private String description;

}
