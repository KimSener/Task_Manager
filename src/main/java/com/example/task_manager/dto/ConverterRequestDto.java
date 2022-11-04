package com.example.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConverterRequestDto {
    private String from;
    private String to;
    private Integer amount;
}
