package com.lopo.service;

import com.lopo.domain.User;


public interface UserService {
    User findUserById(String userId);
}
