package com.example.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lucifer
 * @date: 2018-12-03 16:55
 * @description:
 */
@RestController
@RequestMapping(value = "/hello/")
public class HelloDockerController {

    @GetMapping(value = "say")
    public String sayHello() {
        return "Hell boy, Welcome here";
    }
}
