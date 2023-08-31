package com.miniTest.demo.service;

import com.miniTest.demo.dto.UserDto;
import com.miniTest.demo.request.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers(int page, int limit);

    List<UserDto> getUserByName(String name);

    UserDto getUserById(Integer id);

    UserDto createUser(UserRequest userRequest);

    UserDto updateUser(Integer id, UserRequest userRequest);

    void deleteUser(Integer id);

    void updateAvatar(Integer id, UserRequest userRequest);
}
