package com.example.habitosapi.service.impl;

import com.example.habitosapi.dto.ChartsResponseDto;
import com.example.habitosapi.dto.HabitDto;
import com.example.habitosapi.dto.HabitMapper;
import com.example.habitosapi.entity.Habit;
import com.example.habitosapi.entity.User;
import com.example.habitosapi.exception.HabitRegisteredTodayException;
import com.example.habitosapi.exception.ResourceNotFoundException;
import com.example.habitosapi.repository.HabitRepository;
import com.example.habitosapi.repository.UserRepository;
import com.example.habitosapi.service.IHabitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitService implements IHabitService {

    private HabitRepository habitRepository;
    private UserRepository userRepository;

    @Override
    public void saveHabit(HabitDto habitDto) {
        Optional<User> user = userRepository.findById(habitDto.getUserId());
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("user", "id", habitDto.getUserId());
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<Habit> existingHabit = habitRepository.findHabitForDay(habitDto.getUserId(),
                Date.valueOf(fmt.format(LocalDate.parse(habitDto.getRegistrationDate(), fmt))));
        if (existingHabit.isPresent()) {
            throw new HabitRegisteredTodayException("Ya hay un hábito registrado para la fecha " + habitDto.getRegistrationDate());
        }
        Habit toSave = HabitMapper.toHabit(habitDto, new Habit());
        toSave.setUser(user.get());
        habitRepository.save(toSave);
    }

    @Override
    public List<HabitDto> fetchAllHabitsForUser(Long userId) {
        Optional<List<Habit>> habits = habitRepository.findByUserId(userId);
        return habits.map(habitList -> habitList.stream()
                .map(habit -> new HabitDto(habit.getId(), habit.getWater(), habit.getExercise(),
                        habit.getSleep(), habit.getMoodScore(), habit.getRegistrationDate().toString(), userId))
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    @Override
    public ChartsResponseDto fetchGraphicsHabits(Long userId, String date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date to = Date.valueOf(fmt.format(LocalDate.parse(date, fmt)));
        Date from = Date.valueOf(fmt.format(LocalDate.parse(date, fmt).minusDays(2)));
        ChartsResponseDto chartsResponseDto = new ChartsResponseDto();
        Optional<List<Habit>> existing = habitRepository.findChartsDataFor(userId, from, to);
        if(existing.isEmpty()) {
            return chartsResponseDto;
        }
        List<Habit> habits = existing.get();
        chartsResponseDto.setWater(habits.stream().map(Habit::getWater).collect(Collectors.toList()));
        chartsResponseDto.setExercise(habits.stream().map(Habit::getExercise).collect(Collectors.toList()));
        chartsResponseDto.setMood(habits.stream().map(Habit::getMoodScore).collect(Collectors.toList()));
        chartsResponseDto.setSleep(habits.stream().map(Habit::getSleep).collect(Collectors.toList()));
        chartsResponseDto.setDates(habits.stream().map(habit -> habit.getRegistrationDate().toString()).collect(Collectors.toList()));
        return chartsResponseDto;
    }
}
