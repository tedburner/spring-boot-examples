package com.example.springboot.service.kafka.impl;

import com.example.springboot.base.annotation.KafkaMessageAnnotation;
import com.example.springboot.service.kafka.KafkaMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:58
 * @Description:
 */
@Service
public class KafkaMessageServiceImpl implements KafkaMessageService {

    @Value("${kafka.topic}")
    public String topic;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @KafkaMessageAnnotation
    public void sendMessage(String message) {
        kafkaTemplate.send("testTopic", message);
    }

    @Override
    @KafkaMessageAnnotation
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
