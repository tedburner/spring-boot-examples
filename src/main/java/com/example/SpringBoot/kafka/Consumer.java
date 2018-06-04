package com.example.springboot.kafka;

import com.example.springboot.model.DTO.message.SampleMessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author lingjun.jlj
 * @data 2018/4/26
 * @Description:
 */
@Component
public class Consumer {


    @KafkaListener(topics = "testTopic")
    public void processMessage(SampleMessageDTO message) {
        System.out.println("Received sample message [" + message + "]");
    }

}
