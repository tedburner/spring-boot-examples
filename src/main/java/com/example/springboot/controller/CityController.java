package com.example.springboot.controller;

import com.example.springboot.model.DO.CityDO;
import com.example.springboot.service.common.CityService;
import com.example.springboot.utils.http.NewResponseModel;
import com.example.springboot.utils.redis.RedisUtils;
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
@RequestMapping("/city/")
public class CityController {

    @Resource
    private CityService cityService;

    @RequestMapping(value = "findCityByName")
    public NewResponseModel getCityByName(String name) {
        NewResponseModel responseModel = NewResponseModel.Success();
        responseModel.setData(cityService.getCityByName(name));
        return responseModel;
    }

    @GetMapping(value = "findCityById")
    public NewResponseModel getCaches(Long id) {
        NewResponseModel responseModel = NewResponseModel.Success();
        CityDO cityDO = cityService.getCityById(id);
        responseModel.setData(cityDO);
        return responseModel;
    }

    @RequestMapping(value = "addCity")
    public NewResponseModel addCity() {
        NewResponseModel responseModel = NewResponseModel.Success();
        cityService.addAddress();
        return responseModel;
    }
}
