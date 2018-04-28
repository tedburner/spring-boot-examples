package com.example.SpringBoot.service;

import com.example.SpringBoot.model.DO.CityDO;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/
public interface CityService {

    CityDO getCityByName(String name);

    CityDO getCityById(Long Id);

    void addAddress();
}
