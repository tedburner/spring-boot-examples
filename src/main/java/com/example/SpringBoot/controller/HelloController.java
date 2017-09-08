package com.example.SpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lingjun.jlj
 * @create 2017-09-08
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "Hello "+name;
    }
}
