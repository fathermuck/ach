package com.achieve.service;

import com.achieve.domain.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUser();

    User findUser(User user);
}
