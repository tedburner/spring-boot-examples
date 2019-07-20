package com.sample.rabbitmq.handler;

import com.rabbitmq.client.Channel;
import com.sample.rabbitmq.constant.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:20
 * @description:
 */
@Slf4j
@Component
public class MqHandler {


    @RabbitListener(queues = {MqConstants.QUEUE_TEST})
    public void handleConsumer(String json, Message message, Channel channel) {
        log.info("消费了一条数据，内容是：" + json);
    }
}
