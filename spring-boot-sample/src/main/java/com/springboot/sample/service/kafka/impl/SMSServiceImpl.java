package com.springboot.sample.service.kafka.impl;

import com.springboot.sample.common.annotation.kafka.KafkaMessageAnnotation;
import com.springboot.sample.domain.DTO.message.SmsMessageDTO;
import com.springboot.sample.service.kafka.ProducerService;
import com.springboot.sample.service.kafka.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2018/11/12 19:18
 * @description:
 */
@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

    @Value("${kafka.SMS-Topic}")
    public String topic;

    @Autowired
    private ProducerService producerService;

    @Override
    @KafkaMessageAnnotation
    public void sendSMS(SmsMessageDTO message) {
        log.info("注解反射后的实体类：" + message);
        producerService.sendMessage(topic, message);
    }
}
