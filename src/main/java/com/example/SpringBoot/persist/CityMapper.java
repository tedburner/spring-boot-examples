package com.example.SpringBoot.persist;

import com.example.SpringBoot.dto.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

public interface CityMapper {

    Long addCity(City city);

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City selectByName(@Param("cityName") String cityName);

    /**
     * 根据城市id，查询城市信息
     *
     * @param id 城市名
     */
    City selectById(@Param("id") Long id);

    List<City> selectCityByProvinceId(Long provinceId);
}
