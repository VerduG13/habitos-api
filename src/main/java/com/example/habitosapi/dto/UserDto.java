package com.example.habitosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;

    public UserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
