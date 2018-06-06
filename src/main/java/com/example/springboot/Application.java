package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @author 蒋灵俊
 * * Spring Boot 应用启动类
 * @ServletComponentScan 控制的过滤器和监听器
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.example.springboot.persist")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
