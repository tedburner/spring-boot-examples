package com.sample.mq.constant;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:04
 * @description: RabbitMQ 常量
 */
public class MqConstants {

    public static final String EXCHANGE_TEST = "test.exchange";

    public static final String QUEUE_TEST = "test.exchange";


    public static final String KAFKA_TOPIC_TEST = "topic.test";


    /** 测试延迟exchange */
    public static final String EXCHANGE_TEST_DEMO_DELAY = "test.demo.delay";
    /**测试延迟处理队列*/
    public static final String QUEUE_TEST_DEMO_DELAY = "test.demo.delay";
    /**测试延迟实际处理exchange*/
    public static final String EXCHANGE_TEST_DEMO_PROCESS = "test.demo.process";
    /**测试实际处理queue*/
    public static final String QUEUE_TEST_DEMO_PROCESS = "test.demo.process";
    /** 测试延时时间 10s */
    public static final int TEST_DEMO_TIMEOUT = 10 * 1000;

}
