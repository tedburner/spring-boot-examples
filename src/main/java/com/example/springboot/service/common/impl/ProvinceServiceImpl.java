package com.example.springboot.service.common.impl;

import com.example.springboot.model.DO.ProvinceDO;
import com.example.springboot.persist.ProvinceMapper;
import com.example.springboot.service.common.ProvinceService;
import com.example.springboot.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-12-04
 **/
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private static final Logger log = LoggerFactory.getLogger(ProvinceServiceImpl.class);

    @Resource
    private ProvinceMapper provinceMapper;

    @Override
    @Cacheable(key = "'provinceById'+#id")
    public ProvinceDO getProvince(Long id) {
        return provinceMapper.selectProvinceById(id);
    }

    @Override
    public void addProvince(ProvinceDO provinceDO) {
        provinceMapper.addProvince(provinceDO);
    }
}
