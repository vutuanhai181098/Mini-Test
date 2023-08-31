package com.miniTest.demo.repository;

import com.miniTest.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUser();
    Optional<User> getUserById(Integer id);
    void save(User user);
    void deleteUser(User user);
}
