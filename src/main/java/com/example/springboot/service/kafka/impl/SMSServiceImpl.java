package com.example.springboot.service.kafka.impl;

import com.example.springboot.common.annotation.KafkaMessageAnnotation;
import com.example.springboot.domain.DTO.message.SMSMessageDTO;
import com.example.springboot.service.kafka.ProducerService;
import com.example.springboot.service.kafka.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:18
 * @description:
 */
@Service
public class SMSServiceImpl implements SMSService {

    @Value("${kafka.SMS-Topic}")
    public String topic;

    @Autowired
    private ProducerService producerService;

    @Override
    @KafkaMessageAnnotation
    public void sendSMS(SMSMessageDTO message) {
        producerService.sendMessage(topic, message);
    }
}
