package com.example.habitosapi.repository;

import com.example.habitosapi.dto.ChartsResponseDto;
import com.example.habitosapi.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId")
    Optional<List<Habit>> findByUserId(Long userId);

    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId AND h.registrationDate = :date")
    Optional<Habit> findHabitForDay(Long userId, Date date);

    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId AND h.registrationDate >= :from AND h.registrationDate <= :to")
    Optional<List<Habit>> findChartsDataFor(Long userId, Date from, Date to);
}
