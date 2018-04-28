package com.example.SpringBoot.service.factory.city;

import com.example.SpringBoot.model.DO.CityDO;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
public interface CityProcessor {

    Integer getTag();

    CityDO process();
}
