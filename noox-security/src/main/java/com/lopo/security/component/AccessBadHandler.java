package com.lopo.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccessBadHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> data = new HashMap<>();
        data.put("code", HttpStatus.UNAUTHORIZED.value());
        data.put("msg", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        data.put("data", "");
        response.setContentType("application/json;charset=utf-8");
        String s = new ObjectMapper().writeValueAsString(data);
        response.getWriter().println(s);
    }
}
