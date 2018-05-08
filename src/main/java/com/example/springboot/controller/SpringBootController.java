package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lingjun.jlj
 * @create 2017-09-01
 **/
@RestController
public class SpringBootController {


    @GetMapping("/")
    String springboot() {
        return "Hello Spring Boot!";
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "Hello "+name;
    }
}
