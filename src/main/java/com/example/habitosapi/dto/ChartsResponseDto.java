package com.example.habitosapi.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartsResponseDto {

    private List<Integer> water;
    private List<Integer> exercise;
    private List<Integer> sleep;
    private List<Integer> mood;
    private List<String> dates;

}
