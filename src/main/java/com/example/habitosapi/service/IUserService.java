package com.example.habitosapi.service;

import com.example.habitosapi.dto.UserDto;
import com.example.habitosapi.entity.User;

import java.util.List;

public interface IUserService {

    User save(UserDto user);
//    void delete(UserDto user);
    List<UserDto> findAll();
    UserDto authenticate(String email, String password);
}
