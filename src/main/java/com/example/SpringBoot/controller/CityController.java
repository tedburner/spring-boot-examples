package com.example.SpringBoot.controller;

import com.example.SpringBoot.dto.DO.CityDO;
import com.example.SpringBoot.service.CityService;
import com.example.SpringBoot.utils.http.NewResponseModel;
import com.example.SpringBoot.utils.redis.RedisUtils;
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
    @GetMapping(value = "/city/{id}")
    public NewResponseModel getCaches(@PathVariable("id")Long id){
        NewResponseModel responseModel = NewResponseModel.Success();
        CityDO cityDO = cityService.getCityById(id);
        responseModel.setData(cityDO);
        return responseModel;
    }
}
