package com.sample.mq.config.rabbitmq;

import com.sample.mq.constant.MqConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:05
 * @description: 队列配置
 */
@Configuration
public class QueueConfig {

    @Bean
    public Queue testQueue() {
        return new Queue(MqConstants.QUEUE_TEST, false, false, true);
    }


    /**
     * 设置消息过期死信队列，注意：每次修改队列时间，需要删除旧的队列信息
     *
     * @return
     */
    @Bean
    public Queue delayMsgExpireQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", MqConstants.EXCHANGE_MSG_EXPIRE_PROCESS);
        args.put("x-dead-letter-routing-key", MqConstants.QUEUE_MSG_EXPIRE_PROCESS);
        return new Queue(MqConstants.QUEUE_MSG_EXPIRE, true, false, false, args);
    }

    /**
     * 设置消息过期实际处理队列
     *
     * @return
     */
    @Bean
    public Queue processMsgExpireQueue() {
        return new Queue(MqConstants.QUEUE_MSG_EXPIRE_PROCESS, false, false, true);
    }

    /**
     * 延迟队列设置
     *
     * @return
     */
    @Bean
    public Queue delayQueueExpire() {
        Map<String, Object> params = new HashMap<>(4);
        // 消息到时候发送到该指定exchange
        params.put("x-dead-letter-exchange", MqConstants.EXCHANGE_QUEUE_EXPIRE_PROCESS);
        // 发送到该exchange指定的路由
        params.put("x-dead-letter-routing-key", MqConstants.QUEUE_QUEUE_EXPIRE_PROCESS);
        // 消息的延迟时间
        params.put("x-message-ttl", MqConstants.QUEUE_EXPIRE_TIMEOUT);
        //控制 queue 被自动删除前可以处于未使用状态的时间,20s
//        params.put("x-expires", 20 * 1000);
        return new Queue(MqConstants.QUEUE_QUEUE_EXPIRE_DELAY, true, false, false, params);
    }

    /**
     * 延迟队列实际处理队列
     *
     * @return
     */
    @Bean
    public Queue processQueueExpire() {
        return QueueBuilder
                .durable(MqConstants.QUEUE_QUEUE_EXPIRE_PROCESS)
                .build();
    }

    /**
     * 优先级队列
     *
     * @return
     */
    @Bean
    public Queue priorityQueue() {
        Map<String, Object> params = new HashMap<>(4);
        params.put("x-max-priority", 10);
        return new Queue(MqConstants.QUEUE_PRIORITY, true, false, false, params);
    }
}
