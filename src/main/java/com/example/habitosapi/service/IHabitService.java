package com.example.habitosapi.service;

import com.example.habitosapi.dto.ChartsResponseDto;
import com.example.habitosapi.dto.HabitDto;
import com.example.habitosapi.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface IHabitService  {
    void saveHabit(HabitDto habit);
    List<HabitDto> fetchAllHabitsForUser(Long userId);
    ChartsResponseDto fetchGraphicsHabits(Long userId, String date);
}
