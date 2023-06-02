package com.akshay.blog.services;

import com.akshay.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, int userId);
    UserDto getUserById(int id);
    List<UserDto> getAllUsers();
    void deleteUser(int id);
}
