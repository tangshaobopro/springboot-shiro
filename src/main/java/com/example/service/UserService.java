package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {

    User selectUserByName(String name);
    User selectUserById(Long id);
    List<String> selectPermsByUserId(Long id);
}
