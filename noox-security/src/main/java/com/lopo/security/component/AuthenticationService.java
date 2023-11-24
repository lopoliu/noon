package com.lopo.security.component;

import com.lopo.domain.User;
import com.lopo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class AuthenticationService implements UserDetailsService {

    private UserMapper userMapper;

    @Autowired
    public void setSecurityMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(username);

        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("用户不存在");
        }

        return user;

    }
}
