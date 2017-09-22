package com.example.SpringBoot.controller;

import com.example.SpringBoot.dto.City;
import com.example.SpringBoot.service.CityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/
@RestController
public class CityController {

    @Resource
    private CityService cityService;

    @RequestMapping(value = "/city/{name}")
    public City getCityByName(@PathVariable("name")String name){
        return cityService.getCityByName(name);
    }
}
