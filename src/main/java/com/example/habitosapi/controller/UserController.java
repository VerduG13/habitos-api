package com.example.habitosapi.controller;

import com.example.habitosapi.dto.UserDto;
import com.example.habitosapi.entity.User;
import com.example.habitosapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class UserController {

    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@Validated @RequestBody UserDto user) {
        User createdUser = userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserDto(user.getId(), user.getEmail()));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> authenticate(@Validated @RequestBody UserDto user) {
        UserDto authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());
        authenticatedUser.setPassword(null);
        return ResponseEntity.ok(authenticatedUser);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
