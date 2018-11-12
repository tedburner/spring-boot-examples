package com.example.springboot.common.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:23
 * @description:
 */
public class SMSPushClient {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "SMS-Topic")
    public void SMSPushClient(String message) {

    }
}
