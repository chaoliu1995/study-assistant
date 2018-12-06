package com.chaoliu1995.english;

import com.chaoliu1995.english.filter.LoginFilter;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@tk.mybatis.spring.annotation.MapperScan("com.chaoliu1995.english.dao")
@SpringBootApplication
@EnableJms
public class EnglishApplication {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new LoginFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(EnglishApplication.class, args);
    }
}
