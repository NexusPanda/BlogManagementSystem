package com.Blog.BlogManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BlogManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(BlogManagementSystem.class, args);
    }

}
