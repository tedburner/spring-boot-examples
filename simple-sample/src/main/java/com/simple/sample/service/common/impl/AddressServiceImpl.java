package com.simple.sample.service.common.impl;

import com.simple.sample.domain.entity.City;
import com.simple.sample.domain.entity.Province;
import com.simple.sample.repository.CityRepository;
import com.simple.sample.repository.ProvinceRepository;
import com.simple.sample.service.common.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/20 13:44
 * @description:
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public void addProvince(String name) {
        Province province = new Province();
        province.setName(name);
        provinceRepository.save(province);
    }

    @Override
    public void addCity(Long provinceId, String cityName, String description) {
        City city = new City();
        city.setProvinceId(provinceId);
        city.setName(cityName);
        city.setDescription(description);
        cityRepository.save(city);
    }
}
