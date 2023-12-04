package com.cpr.abyblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cpr.abyblog.mapper")
public class AbyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AbyBlogApplication.class, args);
    }

}