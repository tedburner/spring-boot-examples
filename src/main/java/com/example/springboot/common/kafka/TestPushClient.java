package com.example.springboot.common.kafka;

import com.example.springboot.service.kafka.KafkaMessageService;
import com.example.springboot.utils.sms.SMSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:23
 * @description:
 */
@Slf4j
public class TestPushClient {

    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "test")
    public void SMSPushClient(String msgBody) {
        log.info("start consumer topic test");
        log.info("kafka msg is " + msgBody);
    }
}
