package com.sample.springboot.controller;

import com.sample.springboot.domain.DO.CityDO;
import com.sample.springboot.service.common.CityService;
import com.sample.springboot.utils.http.NewResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/
@RestController
@RequestMapping("/city/")
public class CityController {

    @Resource
    private CityService cityService;

    @GetMapping(value = "findCityByName")
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
