package com.example.habitosapi.service.impl;

import com.example.habitosapi.dto.UserDto;
import com.example.habitosapi.dto.UserMapper;
import com.example.habitosapi.entity.User;
import com.example.habitosapi.exception.EmailAlreadyExistsException;
import com.example.habitosapi.exception.InvalidCredentialsException;
import com.example.habitosapi.repository.UserRepository;
import com.example.habitosapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email " + userDto.getEmail() + " already exists");
        }
        User user = UserMapper.toUser(userDto, new User());
        return userRepository.save(user);
    }

//    @Override
//    public void delete(UserDto user) {
//        userRepository.delete(user);
//    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getEmail(), null))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto authenticate(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()) {
            if(!password.equals(existingUser.get().getPassword())) {
                throw new InvalidCredentialsException("Invalid credentials");
            }
        } else {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return UserMapper.toUserDto(existingUser.get(), new UserDto());
    }
}
