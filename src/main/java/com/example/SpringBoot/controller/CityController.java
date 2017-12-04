package com.example.SpringBoot.controller;

import com.example.SpringBoot.dto.City;
import com.example.SpringBoot.service.CityService;
import com.example.SpringBoot.utils.http.NewResponseModel;
import com.example.SpringBoot.utils.redis.RedisUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/city/{name}")
    public NewResponseModel getCityByName(@PathVariable("name")String name){
        NewResponseModel responseModel = NewResponseModel.Success();
        responseModel.setData(cityService.getCityByName(name));
        return responseModel;
    }
    @GetMapping(value = "/caches/{key}")
    public NewResponseModel getCaches(@PathVariable("key")String key){
        NewResponseModel responseModel = NewResponseModel.Success();
        //redisUtils.set("123", "hello world");
        //System.out.println("进入了方法");
        City city = (City) redisUtils.get("cityId_1");
        responseModel.setData(city);
        //String string= redisUtils.get(key).toString();
        return responseModel;
    }
}
