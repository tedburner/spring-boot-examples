package com.example.springboot.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author lingjun.jlj
 * @data 2018/4/26
 * @Description:
 */
@Component
public class Consumer {
    public static Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "testTopic")
    public void processMessage(ConsumerRecord<?, ?> cr) {
        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
    }

}
