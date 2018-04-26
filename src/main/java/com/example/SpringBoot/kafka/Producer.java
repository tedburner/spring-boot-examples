package com.example.SpringBoot.kafka;

import com.example.SpringBoot.dto.DTO.message.SampleMessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lingjun.jlj
 * @data 2018/4/26
 * @Description:
 */
@Component
public class Producer {

    private final KafkaTemplate<Object, SampleMessageDTO> kafkaTemplate;

    Producer(KafkaTemplate<Object, SampleMessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(SampleMessageDTO message) {
        this.kafkaTemplate.send("testTopic", message);
        System.out.println("Sent sample message [" + message + "]");
    }
}
