package com.simple.sample.controller;

import com.simple.sample.domain.entity.User;
import com.simple.sample.service.common.UserService;
import com.simple.sample.util.http.NewResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: kiturone
 * @date: 2019-08-03 23:02
 * @description: 用户模块
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list/all")
    public NewResponseModel<List<User>> getAllUser() {
        return NewResponseModel.Success(userService.findAll());
    }

    @GetMapping(value = "/batch/add")
    public NewResponseModel batchAdd(Integer num) {
        return NewResponseModel.Success(num);
    }
}
