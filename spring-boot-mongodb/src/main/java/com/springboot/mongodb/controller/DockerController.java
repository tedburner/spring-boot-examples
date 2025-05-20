package com.example.SpringBoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kiturone
 * @data 2018/4/24
 * @Description:
 */
@RestController
public class DockerController {

    @RequestMapping("/docker")
    public String docker(){
        return "Hello Docker!";
    }
}
