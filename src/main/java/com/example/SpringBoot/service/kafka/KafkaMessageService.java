package com.example.springboot.service.kafka;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:58
 * @Description:
 */
public interface KafkaMessageService {

    void  sendMessage(String message);
}
