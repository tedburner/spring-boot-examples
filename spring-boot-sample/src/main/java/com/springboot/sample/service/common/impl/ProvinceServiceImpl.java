package com.springboot.sample.service.common.impl;

import com.springboot.sample.domain.DO.ProvinceDO;
import com.springboot.sample.persist.ProvinceMapper;
import com.springboot.sample.service.common.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
