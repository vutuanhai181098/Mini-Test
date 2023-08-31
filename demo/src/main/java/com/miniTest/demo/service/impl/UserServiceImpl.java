package com.miniTest.demo.service.impl;

import com.miniTest.demo.dto.UserDto;
import com.miniTest.demo.exception.ErrorPasswordException;
import com.miniTest.demo.exception.ResourceNotFoundException;
import com.miniTest.demo.mapper.UserMapper;
import com.miniTest.demo.model.User;
import com.miniTest.demo.repository.UserRepository;
import com.miniTest.demo.request.AvatarRequest;
import com.miniTest.demo.request.UpdatePasswordRequest;
import com.miniTest.demo.request.UserRequest;
import com.miniTest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
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
        userRepository.save(user);
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
    public void updateAvatar(Integer id, AvatarRequest avatarRequest) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        user.setAvatar(avatarRequest.getAvatar());
    }

    @Override
    public void updatePassword(Integer id, UpdatePasswordRequest request) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new ErrorPasswordException("Incorrect password");
        }
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new ErrorPasswordException("The new password must not be the same as the old password");
        }
        user.setPassword(request.getNewPassword());
    }

    @Override
    public String forgotPassword(Integer id) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found user");
        });
        String newPass = randomPassword();
        user.setPassword(newPass);
        return newPass;
    }

    private String randomPassword() {
        final String CHARACTERS =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]};:',<.>/?";
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()-_=+[{]};:',<.>/?]).{8,21}$";
        Random random = new Random();
        int n = CHARACTERS.length();
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                int idx = random.nextInt(n);
                sb.append(CHARACTERS.charAt(idx));
            }
            if (sb.toString().matches(regex)) {
                return sb.toString();
            }
        } while (true);
    }
}
