package com.example.springboot.service.kafka.impl;

import com.alibaba.fastjson.JSON;
import com.example.springboot.common.annotation.KafkaMessageAnnotation;
import com.example.springboot.service.kafka.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Lucifer
 * @date: 2018/11/13 09:55
 * @description:
 */
@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public void sendMessage(String topic, Object msgBody) {
        log.info("kafka produce a message, topic = " + topic + " message =  " + msgBody);
        String jsonString = JSON.toJSONString(msgBody);
        kafkaTemplate.send(topic, jsonString);
    }
}
