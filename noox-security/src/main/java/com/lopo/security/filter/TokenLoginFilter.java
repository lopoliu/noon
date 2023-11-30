package com.lopo.security.filter;

import com.lopo.domain.po.Permission;
import com.lopo.domain.po.User;
import com.lopo.mapper.PermissionMapper;
import com.lopo.security.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TokenLoginFilter extends OncePerRequestFilter {

    @Resource
    private PermissionMapper permissionMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        // 登陆接口不做处理
        if (url.equals("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        // 没传token会报错
        String token = request.getHeader("token");
        if (Objects.isNull(token)){
            throw new RuntimeException("token无效");
        }
        // 解析token
        User user = JwtUtils.parseJWT(token);
        List<Permission> permissions = permissionMapper.selectPermissionByUserId(user.getId());
        user.setAuthority(permissions);

        // 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticated =
                UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());

        // 配置SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        filterChain.doFilter(request, response);
    }
}
