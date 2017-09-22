package com.example.SpringBoot.service;

import com.example.SpringBoot.dto.City;
import com.example.SpringBoot.persist.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City getCityByName(String name) {
        return cityMapper.findByName(name);
    }
}
