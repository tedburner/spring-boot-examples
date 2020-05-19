package com.springboot.sample.service.kafka;

/**
 * @author: lingjun.jlj
 * @date: 2018/11/13 09:55
 * @description:
 */
public interface ProducerService {

    /**
     * 指定topic发送消息
     *
     * @param topic   主题
     * @param msgBody 消息内容
     */
    void sendMessage(String topic, Object msgBody);
}
