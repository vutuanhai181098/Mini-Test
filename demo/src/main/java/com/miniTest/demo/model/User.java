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
    public static AtomicInteger autoId = new AtomicInteger(0);
    Integer id;
    String name;
    String email;
    String phone;
    String address;
    String avatar;
    String password;

    public User(String name, String email, String phone, String address, String password){
        this.id = autoId.incrementAndGet();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }
}
