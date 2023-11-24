package com.lopo.security.component;

import com.lopo.domain.User;
import com.lopo.security.mapper.SecurityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class AuthenticationService implements UserDetailsService {

    private SecurityMapper securityMapper;

    @Autowired
    public void setSecurityMapper(SecurityMapper securityMapper) {
        this.securityMapper = securityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = securityMapper.selectUserByUsername(username);

        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("用户不存在");
        }

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(), AuthorityUtils.NO_AUTHORITIES
        );
    }
}
