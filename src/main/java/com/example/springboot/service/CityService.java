package com.example.springboot.service;

import com.example.springboot.model.DO.CityDO;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/
public interface CityService {

    CityDO getCityByName(String name);

    CityDO getCityById(Long Id);

    void addAddress();
}
