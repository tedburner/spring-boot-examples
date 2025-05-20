package com.springboot.sample.service.factory.city;

import com.springboot.sample.domain.DO.CityDO;

/**
 * @author kiturone
 * @data 2018/4/28
 * @Description:
 */
public interface CityProcessor {

    Integer getTag();

    CityDO process();
}
