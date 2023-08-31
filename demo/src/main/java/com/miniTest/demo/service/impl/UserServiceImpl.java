package com.miniTest.demo.service.impl;

import com.miniTest.demo.dto.UserDto;
import com.miniTest.demo.exception.ResourceNotFoundException;
import com.miniTest.demo.mapper.UserMapper;
import com.miniTest.demo.model.User;
import com.miniTest.demo.repository.UserRepository;
import com.miniTest.demo.request.UserRequest;
import com.miniTest.demo.response.ErrorResponse;
import com.miniTest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<User> userList = userRepository.getAllUser();
        int from = (page - 1) * limit;
        List<User> list = userList.subList(from, (from + limit));
        return list.stream().map(user -> {
            return userMapper.mapToDto(user);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> userList = userRepository.getAllUser()
                .stream().filter(user -> user.getName().toLowerCase().contains(name.toLowerCase())).toList();
        return userList.stream().map(user -> {
            return userMapper.mapToDto(user);
        }).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        User user = userMapper.mapToModel(userRequest);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDto updateUser(Integer id, UserRequest userRequest) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        userMapper.updateUser(userRequest, user);
        return userMapper.mapToDto(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        userRepository.deleteUser(user);
    }

    @Override
    public void updateAvatar(Integer id, UserRequest userRequest) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        userMapper.updateUser(userRequest, user);
    }
}
