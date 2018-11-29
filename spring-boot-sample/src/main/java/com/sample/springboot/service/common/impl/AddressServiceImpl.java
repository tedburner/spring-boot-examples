package com.sample.springboot.service.common.impl;

import com.sample.springboot.domain.DO.ProvinceDO;
import com.sample.springboot.service.common.AddressService;
import com.sample.springboot.service.common.CityService;
import com.sample.springboot.service.common.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Lucifer
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
