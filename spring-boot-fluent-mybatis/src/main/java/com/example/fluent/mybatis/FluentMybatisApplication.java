package com.example.fluent.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.example.fluent.mybatis.mapper"})
public class FluentMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluentMybatisApplication.class, args);
    }

}
