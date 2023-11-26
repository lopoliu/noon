package com.lopo.service.impl;

import com.lopo.domain.Permission;
import com.lopo.domain.User;
import com.lopo.mapper.UserMapper;
import com.lopo.service.PermissionService;
import com.lopo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private PermissionService permissionService;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public User findUserById(String userId) {
        User user = userMapper.selectUserById(userId);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        List<Permission> permissionList = permissionService.findPermissionByUserId(user.getId());
        user.setAuthority(permissionList);
        return user;
    }

    @Override
    public User findUserByUsername(String username){
        User user = userMapper.selectUserByUsername(username);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        List<Permission> permissionList = permissionService.findPermissionByUserId(user.getId());
        user.setAuthority(permissionList);
        return user;
    }

}
