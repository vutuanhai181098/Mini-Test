package com.miniTest.demo.mapper.impl;

import com.miniTest.demo.dto.UserDto;
import com.miniTest.demo.mapper.UserMapper;
import com.miniTest.demo.model.User;
import com.miniTest.demo.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User mapToModel(UserRequest userRequest) {
        String name = userRequest.getName();
        String email = userRequest.getEmail();
        String phone = userRequest.getPhone();
        String address = userRequest.getAddress();
        String password = userRequest.getPassword();
        return new User(name, email, phone, address, password);
    }

    @Override
    public void updateUser(UserRequest userRequest, User user) {
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
    }
}
