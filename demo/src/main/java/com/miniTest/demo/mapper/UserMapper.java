package com.miniTest.demo.mapper;

import com.miniTest.demo.dto.UserDto;
import com.miniTest.demo.model.User;
import com.miniTest.demo.request.UserRequest;

public interface UserMapper {
    UserDto mapToDto(User user);

    User mapToModel(UserRequest userRequest);

    void updateUser(UserRequest userRequest, User user);
}
