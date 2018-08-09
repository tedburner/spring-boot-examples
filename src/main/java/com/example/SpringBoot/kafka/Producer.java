package com.example.springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lingjun.jlj
 * @data 2018/4/26
 * @Description:
 */
@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public  void send() {
        String message = "hello,kafka  " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.kafkaTemplate.send("testTopic", message);
        System.out.println("Sent sample message [" + message + "]");
    }
}
