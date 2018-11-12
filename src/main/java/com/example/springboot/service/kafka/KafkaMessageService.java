package com.example.springboot.service.kafka;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:58
 * @Description:
 */
public interface KafkaMessageService {

    /**
     * 固定topic发送消息
     *
     * @param message 消息内容
     */
    void sendMessage(String message);

    /**
     * 指定topic发送消息
     *
     * @param topic   主题
     * @param message 消息内容
     */
    void sendMessage(String topic, String message);


    /**
     * 更新数据库kafka消息状态
     */
    void updateKafkaMessage();
}
