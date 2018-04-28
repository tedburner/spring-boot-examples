package com.example.SpringBoot.service.impl;

import com.example.SpringBoot.model.DO.CityDO;
import com.example.SpringBoot.service.DemoService;
import com.example.SpringBoot.service.factory.city.CityFactory;
import com.example.SpringBoot.service.factory.city.CityProcessor;
import com.example.SpringBoot.service.factory.test.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private TestFactory testFactory;
    @Autowired
    private CityFactory cityFactory;

    @Override
    public void show() {
        try {
            testFactory.process(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showCity() {
        CityProcessor processor = cityFactory.getCityProcessor(0);
        CityDO cityDO = processor.process();
        System.out.println(cityDO);
    }

}
