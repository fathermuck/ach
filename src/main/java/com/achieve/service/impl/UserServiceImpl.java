package com.achieve.service.impl;

import com.achieve.domain.User;
import com.achieve.mapper.UserMapper;
import com.achieve.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findUser(User user) {
        return userMapper.findUser(user);
    }
}
