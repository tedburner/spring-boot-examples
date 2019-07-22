package com.sample.mq.handler;

import com.sample.mq.constant.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/22 16:20
 * @version：1.0.0
 * @description: kafka 消息处理类
 */
@Slf4j
@Component
public class KafkaHandler {


    @KafkaListener(topics = MqConstants.KAFKA_TOPIC_TEST)
    public void handleKafka(ConsumerRecord<String, String> record){
        log.info("kafka processMessage start");
        log.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());
    }
}
