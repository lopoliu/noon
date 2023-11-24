package com.lopo.security;


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
}
