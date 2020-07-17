package com.sample.mq.constant;

/**
 * @author: lingjun.jlj
 * @date: 2019-07-19 23:04
 * @description: RabbitMQ 常量
 */
public class MqConstants {

    public static final String EXCHANGE_TEST = "test.exchange";

    public static final String QUEUE_TEST = "test.exchange";

    public static final String KAFKA_TOPIC_TEST = "topic.test";


    /** 测试延迟exchange */
    public static final String EXCHANGE_QUEUE_EXPIRE_DELAY = "queue.expire.delay";
    /**测试延迟处理队列*/
    public static final String QUEUE_QUEUE_EXPIRE_DELAY = "queue.expire.delay";
    /**测试延迟实际处理exchange*/
    public static final String EXCHANGE_QUEUE_EXPIRE_PROCESS = "queue.expire.process";
    /**测试实际处理queue*/
    public static final String QUEUE_QUEUE_EXPIRE_PROCESS = "queue.expire.process";
    /** 测试延时时间 10s */
    public static final int QUEUE_EXPIRE_TIMEOUT = 10 * 1000;

    /**测试设置消息本身过期*/
    public static final String EXCHANGE_MSG_EXPIRE = "delay.expire.exchange";
    public static final String QUEUE_MSG_EXPIRE = "delay.expire.queue";
    public static final String EXCHANGE_MSG_EXPIRE_PROCESS = "process.expire.exchange";
    public static final String QUEUE_MSG_EXPIRE_PROCESS = "process.expire.queue";

    /**测试消息优先级队列*/
    public static final String EXCHANGE_PRIORITY = "priority.exchange";
    public static final String QUEUE_PRIORITY = "priority.queue";

    public static final String VIDEO_SYNTHESIS = "task_queue";

    /**RocketMQ 队列常量*/
    public static final String ROCKETMQ_TRANSACTION_MSG_TOPIC = "transaction_topic";
    public static final String ROCKETMQ_TRANSACTION_PRODUCER_GROUP = "transaction_group";


}
