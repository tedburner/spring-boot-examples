package com.sample.springboot.service.factory.city;

import com.sample.springboot.domain.DO.CityDO;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
public interface CityProcessor {

    Integer getTag();

    CityDO process();
}
