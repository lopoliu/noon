package com.lopo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lopo.*")
public class NooxApp {
    public static void main(String[] args) {
        SpringApplication.run(NooxApp.class, args);
    }
}
