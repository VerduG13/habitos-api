package com.example.habitosapi.dto;

import com.example.habitosapi.entity.Habit;

public class HabitMapper {

    public static HabitDto toHabitDto(Habit habit, HabitDto habitDto) {
        habitDto.setId(habit.getId());
        habitDto.setWater(habit.getWater());
        habitDto.setExercise(habit.getExercise());
        habitDto.setSleep(habit.getSleep());
        habitDto.setMoodScore(habit.getMoodScore());
        return habitDto;
    }

    public static Habit toHabit(HabitDto habitDto, Habit habit) {
        habit.setId(habit.getId());
        habit.setWater(habit.getWater());
        habit.setExercise(habit.getExercise());
        habit.setSleep(habit.getSleep());
        habit.setMoodScore(habit.getMoodScore());
        return habit;
    }
}
