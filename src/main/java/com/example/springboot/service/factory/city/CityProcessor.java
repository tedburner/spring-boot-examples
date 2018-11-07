package com.example.springboot.service.factory.city;

import com.example.springboot.model.DO.CityDO;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
public interface CityProcessor {

    Integer getTag();

    CityDO process();
}
