package com.example.service.impl;

import com.example.entity.User;
import com.example.mappers.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByName(String name) {
        User user = new User();
        user.setUserName(name);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",name);
        user = userMapper.selectOneByExample(example);
        return user;
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<String> selectPermsByUserId(Long id) {
        return userMapper.selectPermsByUserId(id);
    }
}
