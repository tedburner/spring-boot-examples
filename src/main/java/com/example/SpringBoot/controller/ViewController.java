package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.DO.UserDO;
import com.example.SpringBoot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lingjun.jlj
 * @data 2018/4/2
 */
@RestController
@RequestMapping(value = "/view")
public class ViewController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/getView")
    public ModelAndView view(){
        List<UserDO> users=userService.findUser();
        System.out.println(users);
        ModelAndView model = new ModelAndView("index");
        model.addObject("users", users);
        return model;
    }
}
