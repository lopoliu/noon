package com.lopo.service;

import com.lopo.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findPermissionByUserId(Long userId);
}
