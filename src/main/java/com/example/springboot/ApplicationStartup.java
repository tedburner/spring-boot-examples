package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * @author 蒋灵俊
 * @data 2018/6/6
 * @Description:
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.example.springboot.persist")
public class ApplicationStartup extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartup.class, args);
    }
}
