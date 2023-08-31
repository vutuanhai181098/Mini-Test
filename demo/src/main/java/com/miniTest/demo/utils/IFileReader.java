package com.miniTest.demo.utils;

import com.miniTest.demo.model.User;

import java.util.List;

public interface IFileReader {
    List<User> readFile(String filePath);
}
