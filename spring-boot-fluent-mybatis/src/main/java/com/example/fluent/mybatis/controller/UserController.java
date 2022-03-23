package com.example.fluent.mybatis.controller;

import com.example.fluent.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private UserService userService;

    @GetMapping(value = "get")
    public Object getAll(Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "get/{name}")
    public Object getUserInfoByName(@PathVariable("name") String name) {
        return userService.findByName(name);
    }

    @GetMapping(value = "save")
    public Object save() {
        userService.save();
        return "success";
    }
}
