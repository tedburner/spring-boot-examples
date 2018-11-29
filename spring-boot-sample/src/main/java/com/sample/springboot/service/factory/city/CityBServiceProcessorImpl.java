package com.sample.springboot.service.factory.city;

import com.sample.springboot.common.enums.SimpleEnum;
import com.sample.springboot.domain.DO.CityDO;
import com.sample.springboot.persist.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
@Service
public class CityBServiceProcessorImpl implements CityProcessor {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public Integer getTag() {
        return SimpleEnum.B.getCode();
    }

    @Override
    public CityDO process() {
        return cityMapper.selectById(2L);
    }
}
