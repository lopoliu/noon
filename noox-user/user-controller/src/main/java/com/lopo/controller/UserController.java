package com.lopo.controller;


import com.lopo.domain.User;
import com.lopo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('user:select')")
    public User userDetail(@PathVariable("userId") String userId){
        return userService.findUserById(userId);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('user:test')")
    public String userTest(){
        return "用于测试访问权限不足的接口";
    }
}
