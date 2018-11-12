package com.example.springboot.service.common;

import com.example.springboot.model.DO.CityDO;

import java.util.List;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/
public interface CityService {

    CityDO getCityByName(String name);

    CityDO getCityById(Long Id);

    void addAddress();

    /**
     * 根据省份查询城市信息
     *
     * @param provinceId
     */
    List<CityDO> findCityByProvinceId(Long provinceId);
}
