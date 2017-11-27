package com.example.SpringBoot.service;

import com.example.SpringBoot.dto.City;
import com.example.SpringBoot.persist.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

@Service
public class CityServiceImpl implements CityService{

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public City getCityByName(String name) {
        return cityMapper.findByName(name);
    }

    @Override
    public City getCityById(Long id) {

        return cityMapper.findById(id);
    }

}
