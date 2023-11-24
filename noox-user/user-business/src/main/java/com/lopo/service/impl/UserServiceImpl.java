package com.lopo.service.impl;

import com.lopo.domain.User;
import com.lopo.mapper.UserMapper;
import com.lopo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserById(String userId) {
        User user = userMapper.selectUserById(userId);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        return user;
    }
}
