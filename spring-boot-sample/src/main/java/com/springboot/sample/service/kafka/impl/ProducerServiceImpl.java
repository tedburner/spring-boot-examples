package com.springboot.sample.service.kafka.impl;

import com.kit.common.util.common.gson.FormatUtils;
import com.springboot.sample.service.kafka.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: kiturone
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
        String jsonString = FormatUtils.obj2str(msgBody);
        kafkaTemplate.send(topic, jsonString);
    }
}
