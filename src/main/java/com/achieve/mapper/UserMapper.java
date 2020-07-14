package com.achieve.mapper;

import com.achieve.domain.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAllUser();

    User findUser(User user);
}
