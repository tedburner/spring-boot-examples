package com.springboot.sample.service.common.impl;

import com.springboot.sample.domain.DO.ProvinceDO;
import com.springboot.sample.service.common.AddressService;
import com.springboot.sample.service.common.CityService;
import com.springboot.sample.service.common.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kiturone
 * @Date: 2018/7/23 14:17
 * @Description:
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public void addAddress() {

        ProvinceDO provinceDO = ProvinceDO.ProvinceDOBuilder
                .aProvinceDO()
                .withName("江苏省")
                .build();
        provinceService.addProvince(provinceDO);

        ProvinceDO provinceDO1 = provinceDO;
        provinceDO.setName("北京市");
        provinceService.addProvince(provinceDO1);

    }
}
