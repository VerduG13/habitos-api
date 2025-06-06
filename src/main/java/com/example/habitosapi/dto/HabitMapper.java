package com.example.habitosapi.dto;

import com.example.habitosapi.entity.Habit;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabitMapper {

    public static HabitDto toHabitDto(Habit habit, HabitDto habitDto) {
        habitDto.setId(habit.getId());
        habitDto.setWater(habit.getWater());
        habitDto.setExercise(habit.getExercise());
        habitDto.setSleep(habit.getSleep());
        habitDto.setMoodScore(habit.getMoodScore());
        habitDto.setUserId(habit.getUser().getId());
        habitDto.setRegistrationDate(habit.getRegistrationDate().toString());
        return habitDto;
    }

    public static Habit toHabit(HabitDto habitDto, Habit habit) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        habit.setId(habitDto.getId());
        habit.setWater(habitDto.getWater());
        habit.setExercise(habitDto.getExercise());
        habit.setSleep(habitDto.getSleep());
        habit.setMoodScore(habitDto.getMoodScore());
        habit.setRegistrationDate(Date.valueOf(fmt.format(LocalDate.parse(habitDto.getRegistrationDate(), fmt))));
        return habit;
    }
}
