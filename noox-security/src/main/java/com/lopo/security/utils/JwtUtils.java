package com.lopo.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lopo.domain.po.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    public static String createJWT(Map<String, Object> userMap){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        System.out.println(calendar.getTime());
        JwtBuilder builder = Jwts.builder();
        builder.addClaims(userMap);   // claims
        builder.setExpiration(calendar.getTime()); // 过期时间
        return builder.compact();
    }
    public static User parseJWT(String token){
        try {
            Object s = Jwts.parser().parse(token).getBody();
            // 转换为User对象返回
            return JSON.parseObject(JSONObject.toJSONString(s), User.class);
        }catch (ExpiredJwtException e){
            throw new RuntimeException("token过期");
        }catch (Exception e){
            throw new RuntimeException("token无效");
        }
    }
}
