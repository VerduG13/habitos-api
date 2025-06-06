package com.example.habitosapi.exception;

import com.example.habitosapi.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCredentialsException(InvalidCredentialsException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                e.getMessage(), HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HabitRegisteredTodayException.class)
    public ResponseEntity<ErrorResponseDto> handleHabitRegisteredTodayException(HabitRegisteredTodayException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                e.getMessage(), HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
