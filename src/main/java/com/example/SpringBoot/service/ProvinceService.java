package com.example.SpringBoot.service;

import com.example.SpringBoot.dto.ProvinceDO;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-12-04
 **/
public interface ProvinceService {
    ProvinceDO getProvince(Long id);

}
