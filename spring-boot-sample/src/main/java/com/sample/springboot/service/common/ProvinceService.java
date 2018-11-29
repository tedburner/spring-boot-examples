package com.sample.springboot.service.common;

import com.sample.springboot.domain.DO.ProvinceDO;

/**
 * @author Lucifer
 * @create 2017-12-04
 **/
public interface ProvinceService {

    ProvinceDO getProvince(Long id);

    void addProvince(ProvinceDO provinceDO);

}
