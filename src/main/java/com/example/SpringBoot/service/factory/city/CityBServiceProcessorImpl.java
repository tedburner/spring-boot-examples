package com.example.SpringBoot.service.factory.city;

import com.example.SpringBoot.enums.SimpleEnum;
import com.example.SpringBoot.model.DO.CityDO;
import com.example.SpringBoot.persist.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
@Service
public class CityBServiceProcessorImpl implements CityProcessor{

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
