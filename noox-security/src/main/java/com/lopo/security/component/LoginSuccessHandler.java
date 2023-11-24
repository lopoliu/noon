package com.lopo.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 200);
        data.put("msg", "success");
        data.put("data", "token");
        response.setContentType("application/json;charset=utf-8");
        String s = new ObjectMapper().writeValueAsString(data);
        response.getWriter().println(s);
    }
}
