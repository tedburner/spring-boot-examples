package com.springboot.sample.service.common;

import com.springboot.sample.domain.DO.ProvinceDO;

/**
 * @author Lucifer
 * @create 2017-12-04
 **/
public interface ProvinceService {

    ProvinceDO getProvince(Long id);

    void addProvince(ProvinceDO provinceDO);

}
