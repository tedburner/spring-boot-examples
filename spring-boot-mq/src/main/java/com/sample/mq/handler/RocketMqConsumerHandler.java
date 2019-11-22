package com.sample.mq.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/22 10:57
 * @description: RocketMq 消费者处理类
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.rocketmq.topic}", consumerGroup = "my-group")
public class RocketMqConsumerHandler implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        log.info("RocketMq 消费了一条消息，内容：{}", s);
    }
}
