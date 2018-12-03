package com.sample.springboot.service.kafka.impl;

import com.sample.springboot.common.annotation.KafkaMessageAnnotation;
import com.sample.springboot.domain.DTO.message.SMSMessageDTO;
import com.sample.springboot.service.kafka.ProducerService;
import com.sample.springboot.service.kafka.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: Lucifer
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
    public void sendSMS(SMSMessageDTO message) {
        log.info("注解反射后的实体类：" + message);
        producerService.sendMessage(topic, message);
    }
}
