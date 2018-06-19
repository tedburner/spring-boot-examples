package com.example.springboot.controller;

import com.example.springboot.base.annotation.KafkaMessageAnnotation;
import com.example.springboot.kafka.Producer;
import com.example.springboot.service.kafka.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 蒋灵俊
 * @data 2018/6/6
 * @Description:
 */
@RestController
public class SampleController {

    @Autowired
    private Producer producer;

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @RequestMapping("send")
    public void sendMessage(String message){
        kafkaMessageService.sendMessage(message);
    }
}
