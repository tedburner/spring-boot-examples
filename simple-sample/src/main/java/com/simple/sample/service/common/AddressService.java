package com.simple.sample.service.common;

/**
 * @author: kiturone
 * @date: 2019/9/20 13:44
 * @description:
 */
public interface AddressService {

    /**
     * 添加省份
     *
     * @param name 省份名称
     */
    void addProvince(String name);

    /**
     * 添加城市信息
     *
     * @param provinceId  省份id
     * @param cityName    城市名字
     * @param description 城市描述
     */
    void addCity(Long provinceId, String cityName, String description);
}
