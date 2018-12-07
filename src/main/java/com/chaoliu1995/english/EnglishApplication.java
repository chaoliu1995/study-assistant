package com.chaoliu1995.english;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@tk.mybatis.spring.annotation.MapperScan("com.chaoliu1995.english.dao")
@SpringBootApplication
@EnableJms
public class EnglishApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnglishApplication.class, args);
    }
}
