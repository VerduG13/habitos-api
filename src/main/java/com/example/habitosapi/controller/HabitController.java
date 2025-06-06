package com.example.habitosapi.controller;

import com.example.habitosapi.dto.ChartRequestDto;
import com.example.habitosapi.dto.ChartsResponseDto;
import com.example.habitosapi.dto.HabitDto;
import com.example.habitosapi.dto.UserDto;
import com.example.habitosapi.service.IHabitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/habit")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class HabitController {
    private IHabitService habitService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitDto>> getHabits(@PathVariable String userId) {
        List<HabitDto> habitDtoList = habitService.fetchAllHabitsForUser(Long.parseLong(userId));
        return ResponseEntity.ok(habitDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity create(@Validated @RequestBody HabitDto habit) {
        habitService.saveHabit(habit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/chartData")
    public ResponseEntity<ChartsResponseDto> getChartData(@RequestBody ChartRequestDto chartRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(habitService.fetchGraphicsHabits(
                Long.parseLong(chartRequestDto.getUserId()), chartRequestDto.getDate()));
    }
}
