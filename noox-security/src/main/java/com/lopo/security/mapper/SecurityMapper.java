package com.lopo.security.mapper;


import com.lopo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SecurityMapper {
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return User
     */
    @Select("select * from user where username=#{username}")
    User selectUserByUsername(@Param("username") String username);
}
