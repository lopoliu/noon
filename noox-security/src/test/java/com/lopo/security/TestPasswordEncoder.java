package com.lopo.security;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lopo.domain.po.User;
import com.lopo.security.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordEncoder {
    @Test
    public void passwordEncoderTest(){
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = cryptPasswordEncoder.encode("123456");
        System.out.println(encode);
        boolean result = cryptPasswordEncoder.matches("123456", "$2a$10$qwjPiz8aBDubO6KHJf6s8uUYbEdT4TxEhsvFcFbmpwG7aM/o6khte");
        System.out.println(result);
    }


    /**
     * 测试创建jwt
     */
    @Test
    public void jwtCreateTest(){
        User user = new User();
        user.setUsername("ss");
        user.setId(1L);
        String jwt = JwtUtils.createJWT(user.toMap());
        System.out.println(jwt);
    }

    /**
     * 测试解析jwt
     */
    @Test
    public void jwtParseTest(){
        Object s = JwtUtils.parseJWT("eyJhbGciOiJub25lIn0.eyJpZCI6MSwidXNlcm5hbWUiOiJzcyJ9.");
        User user = JSON.parseObject(JSONObject.toJSONString(s), User.class);
        System.out.println(user.getUsername());
    }
}
