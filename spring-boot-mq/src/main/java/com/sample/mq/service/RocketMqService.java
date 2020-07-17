package com.sample.mq.service;

/**
 * @author: lingjun.jlj
 * @date: 2020/7/17 23:31
 * @description:
 */
public interface RocketMqService {


    /**
     * 发送事务消息
     */
    void sendTransactionMsg();
}
