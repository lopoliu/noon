package com.lopo.controller;


import com.lopo.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private AuthenticationManager authenticationManager;

    @GetMapping("/{userId}")
    public String userDetail(@PathVariable("userId") String userId){
        return "userDetail"+userId;
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody User user){
        return "sss";
//        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(), user.getPassword());
//        Authentication authenticate = authenticationManager.authenticate(unauthenticated);

    }
}
