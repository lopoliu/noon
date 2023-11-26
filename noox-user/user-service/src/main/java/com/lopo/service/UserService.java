package com.lopo.service;

import com.lopo.domain.po.User;


public interface UserService {
    User findUserById(String userId);
    User findUserByUsername(String username);
}
