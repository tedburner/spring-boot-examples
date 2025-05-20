package com.springboot.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: kiturone
 * @data 2018/4/23
 * @Description:
 */
@Controller
@RequestMapping("/templates/")
public class ThymeleafController {

    @GetMapping("main")
    public String index() {

        return "main";
    }
}
