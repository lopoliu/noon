package com.lopo.mapper;


import com.lopo.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<Permission> selectPermissionByUserId(@Param("userId") Long userId);
}
