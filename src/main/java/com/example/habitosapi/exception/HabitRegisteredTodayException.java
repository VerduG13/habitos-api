package com.example.habitosapi.exception;

public class HabitRegisteredTodayException extends RuntimeException {
    public HabitRegisteredTodayException(String message) {
        super(message);
    }
}
