package com.example.jooq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/22 22:22
 * @description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "get/all")
    public Object getAll() {
        return null;
    }
}
