package com.springboot.sample.service.common.impl;

import com.springboot.sample.domain.DO.CityDO;
import com.springboot.sample.domain.DTO.message.SmsMessageDTO;
import com.springboot.sample.service.common.DemoService;
import com.springboot.sample.service.factory.city.CityFactory;
import com.springboot.sample.service.factory.city.CityProcessor;
import com.springboot.sample.service.factory.test.TestFactory;
import com.springboot.sample.service.kafka.ProducerService;
import com.springboot.sample.service.kafka.SMSService;
import com.kit.common.util.common.gson.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kiturone
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
    @Autowired
    private ProducerService producerService;

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
        SmsMessageDTO messageDTO = new SmsMessageDTO();
        messageDTO.setPhone("17826852173");
        messageDTO.setTemplateCode("SMS_150743936");
        Map<String, Object> param = new HashMap<>();
        param.put("code", "1234");
        messageDTO.setTemplateParam(FormatUtils.obj2str(param));
        smsService.sendSMS(messageDTO);
    }

    @Override
    public void sendTestMessage(String message) {
        producerService.sendMessage("test", message);
    }

}
