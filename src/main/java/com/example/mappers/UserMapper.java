package com.example.mappers;

import com.example.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<String> selectPermsByUserId(Long id);
}