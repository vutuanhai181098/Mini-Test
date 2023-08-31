package com.miniTest.demo.model;

import com.miniTest.demo.database.UserDB;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    static AtomicInteger autoId = new AtomicInteger(UserDB.userList.size());
    Integer id;
    String name;
    String email;
    String phone;
    String address;
    String avatar;
    String password;

    public User(String name, String email, String phone, String address, String avatar, String password){
        this.id = autoId.getAndIncrement();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.password = password;
    }
}
