package com.springboot.datajpa.controller;

import com.springboot.datajpa.domain.User;
import com.springboot.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Arthas
 * @date: 2018-12-26 18:41
 * @description:
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    protected UserService userService;

    @GetMapping(value = "findAll")
    public List<User> findAll(){

        return userService.findUserAll();
    }

    @GetMapping(value = "findStream")
    @Transactional(readOnly = true)
    public List<User> findStream(){
        return userService.findUserStream();
    }
}
