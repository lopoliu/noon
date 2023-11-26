package com.lopo.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class UsernamePasswordLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 必须是POST请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 验证响应类型
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            try {
                System.out.println(request.getInputStream().toString());
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, String> map = objectMapper.readValue(request.getInputStream(), Map.class);
                String username = map.get("username");
                String password = map.get("password");
                UsernamePasswordAuthenticationToken unauthenticated = new  UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, unauthenticated);
                return this.getAuthenticationManager().authenticate(unauthenticated);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(request.getParameter("username"));
        return super.attemptAuthentication(request, response);
    }
}