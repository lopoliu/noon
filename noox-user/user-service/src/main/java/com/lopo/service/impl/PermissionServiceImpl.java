package com.lopo.service.impl;

import com.lopo.domain.po.Permission;
import com.lopo.mapper.PermissionMapper;
import com.lopo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {
    private PermissionMapper permissionMapper;

    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> findPermissionByUserId(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }
}
