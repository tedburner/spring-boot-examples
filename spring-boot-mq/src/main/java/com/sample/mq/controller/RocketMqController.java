package com.sample.mq.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/22 11:54
 * @description:
 */
@RestController
@RequestMapping(value = "/rocket")
public class RocketMqController {

    @Value("${mq.rocketmq.topic}")
    private String topic;

    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public RocketMqController(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @GetMapping(value = "/send")
    public String send() {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, "Hello World!");
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", topic, sendResult);
        return "发送成功";
    }
}
