package com.example.habitosapi.dto;

import com.example.habitosapi.entity.User;

public class UserMapper {

    public static UserDto toUserDto(User user, UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User toUser(UserDto userDto, User user) {
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
