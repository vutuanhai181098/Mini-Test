package com.miniTest.demo.repository.impl;

import com.miniTest.demo.database.UserDB;
import com.miniTest.demo.model.User;
import com.miniTest.demo.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAllUser() {
        return UserDB.userList;
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return UserDB.userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(User user) {
        UserDB.userList.add(user);
    }

    @Override
    public void deleteUser(User user) {
        UserDB.userList.remove(user);
    }
}
