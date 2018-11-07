package com.example.springboot.service.common;

import com.example.springboot.model.DO.CityDO;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/
public interface CityService {

    CityDO getCityByName(String name);

    CityDO getCityById(Long Id);

    void addAddress();
}
