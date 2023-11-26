package com.lopo.mapper;

import com.lopo.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectUserById(@Param("userId") String userId);
    User selectUserByUsername(@Param("username") String username);
}
