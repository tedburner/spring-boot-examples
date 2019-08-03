package com.springboot.sample.service.factory.city;

import com.springboot.sample.common.enums.SimpleEnum;
import com.springboot.sample.domain.DO.CityDO;
import com.springboot.sample.persist.CityMapper;
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
