package com.sample.mq.controller;

import com.sample.mq.constant.MqConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:18
 * @description:
 */
@RestController
@RequestMapping(value = "/mq")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/sendMsg")
    public String sendMsg() {
        String message = "你好，今天是" + LocalDate.now();
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_TEST, MqConstants.QUEUE_TEST, message);
        return "发送成功！";
    }
}
