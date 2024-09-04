package com.liukf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liukf.mapper")
public class JingClientApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(JingClientApplication.class, args);
    }
}
