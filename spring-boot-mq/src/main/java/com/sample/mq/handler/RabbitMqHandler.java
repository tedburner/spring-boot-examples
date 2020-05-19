package com.sample.mq.handler;

import com.rabbitmq.client.Channel;
import com.sample.mq.constant.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: lingjun.jlj
 * @date: 2019-07-19 23:20
 * @description:
 */
@Slf4j
@Component
public class RabbitMqHandler {


    @RabbitListener(queues = {MqConstants.QUEUE_TEST})
    public void handleConsumer(String json, Message message, Channel channel) {
        log.info("消费了一条数据，内容是：{}", json);
    }

    /**
     * 消息设置过期时间
     */
    @RabbitListener(queues = {MqConstants.QUEUE_MSG_EXPIRE_PROCESS})
    public void handleMessageExpire(String json, Message message, Channel channel) {
        log.info("消费了一条消息过期的数据，内容是：{}", json);
    }


    /**
     * 实际处理延迟队列消息
     */
    @RabbitListener(queues = {MqConstants.QUEUE_QUEUE_EXPIRE_PROCESS})
    public void handleDelayConsumer(String json, Message message, Channel channel) {
        log.info("处理一条延迟队列的消息，消息内容是：{}", json);
    }

    /**
     * 处理优先级队列消息
     */
    @RabbitListener(queues = {MqConstants.QUEUE_PRIORITY})
    public void handlePriorityConsumer(String json, Message message, Channel channel) {
        try {
            log.info("消费线程沉睡5s");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("处理一条优先队列中的消息，消息内容是：{}", json);

    }
}
