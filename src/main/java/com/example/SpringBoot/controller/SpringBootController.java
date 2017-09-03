package com.example.SpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lingjun.jlj
 * @create 2017-09-01
 **/
@Controller
public class SpringBootController {


    @RequestMapping("/")
    @ResponseBody
    String springboot() {
        return "Hello Spring Boot!";
    }
}
