package com.sample.springboot.service.common.impl;

import com.sample.springboot.domain.DO.ProvinceDO;
import com.sample.springboot.persist.ProvinceMapper;
import com.sample.springboot.service.common.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lucifer
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
