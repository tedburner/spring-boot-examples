package com.springboot.sample.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:23
 * @description:
 */
@Slf4j
@Component
public class TestPushClient {


    //@KafkaListener(topics = "${kafka.topic}")
    public void SMSPushClient(ConsumerRecord<?, ?> record) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info("start consumer topic test");
            //获取消息
            Object message = kafkaMessage.get();

            log.info("Receive： +++++++++++++++ Record:" + record);
            log.info("Receive： +++++++++++++++ Message:" + message);
        }
    }
}
