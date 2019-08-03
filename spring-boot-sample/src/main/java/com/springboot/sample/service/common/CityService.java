package com.springboot.sample.service.common;

import com.springboot.sample.domain.DO.CityDO;

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
