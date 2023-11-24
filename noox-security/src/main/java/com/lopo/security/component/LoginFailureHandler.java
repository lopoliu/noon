package com.lopo.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 500);
        data.put("msg", "error");
        data.put("data", exception.getMessage() );
        response.setContentType("application/json;charset=utf-8");
        String s = new ObjectMapper().writeValueAsString(data);
        response.getWriter().println(s);
    }
}
