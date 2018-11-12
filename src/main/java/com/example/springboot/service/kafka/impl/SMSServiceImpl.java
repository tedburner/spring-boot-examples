package com.example.springboot.service.kafka.impl;

import com.example.springboot.common.annotation.KafkaMessageAnnotation;
import com.example.springboot.service.kafka.SMSService;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:18
 * @description:
 */
public class SMSServiceImpl implements SMSService {

    private static final String topic = "SMS-Topic";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @KafkaMessageAnnotation
    public void sendSMS(String message) {
        kafkaTemplate.send(topic, message);
    }
}
