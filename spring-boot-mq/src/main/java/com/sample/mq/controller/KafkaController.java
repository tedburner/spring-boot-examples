package com.sample.mq.controller;

import com.kit.common.util.common.gson.FormatUtils;
import com.sample.mq.constant.MqConstants;
import com.sample.mq.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: kiturone
 * @date: 2019/7/22 16:17
 * @version：1.0.0
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    //@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping(value = "/send")
    public void send() {
        log.info("向kafka发送消息===============");
        Message message = Message.builder()
                .id(System.currentTimeMillis())
                .msg("今天是" + new Date() + " 天气真不错！！！")
                .build();
        kafkaTemplate.send(MqConstants.KAFKA_TOPIC_TEST, FormatUtils.obj2str(message));

    }
}
