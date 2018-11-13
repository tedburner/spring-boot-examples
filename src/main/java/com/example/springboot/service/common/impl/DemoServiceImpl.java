package com.example.springboot.service.common.impl;

import com.example.springboot.domain.DO.CityDO;
import com.example.springboot.domain.DTO.message.SMSMessageDTO;
import com.example.springboot.service.common.DemoService;
import com.example.springboot.service.factory.city.CityFactory;
import com.example.springboot.service.factory.city.CityProcessor;
import com.example.springboot.service.factory.test.TestFactory;
import com.example.springboot.service.kafka.SMSService;
import com.example.springboot.utils.common.gson.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private TestFactory testFactory;
    @Autowired
    private CityFactory cityFactory;
    @Autowired
    private SMSService smsService;

    @Override
    public void show() {
        try {
            testFactory.process(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCity() {
        CityProcessor processor = cityFactory.getCityProcessor(0);
        CityDO cityDO = processor.process();
        System.out.println(cityDO);
    }

    @Override
    public int getNumer() {
        return 1 / 0;
    }

    @Override
    public void sendMessage() {
        SMSMessageDTO messageDTO = new SMSMessageDTO();
        messageDTO.setPhone("17826852173");
        messageDTO.setTemplateCode("SMS_150743936");
        Map<String, Object> param = new HashMap<>();
        param.put("code", "1234");
        messageDTO.setTemplateParam(FormatUtils.obj2str(param));
        smsService.sendSMS(messageDTO);
    }

}
