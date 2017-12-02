package com.example.SpringBoot.service;

import com.example.SpringBoot.dto.City;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/
public interface CityService {

    City getCityByName(String name);

    City getCityById(Long Id);

    void addAddress();
}
