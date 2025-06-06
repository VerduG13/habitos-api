package com.example.habitosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HabitDto {
    private Long id;
    private int water;
    private int exercise;
    private int sleep;
    private int moodScore;
    private String registrationDate;
    private Long userId;
}
