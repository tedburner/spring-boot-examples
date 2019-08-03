package com.springboot.sample.service.kafka;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:58
 * @Description:
 */
public interface KafkaMessageService {


    /**
     * 消费者消息后修改kafkaMessage消息状态
     *
     * @param status
     * @param kafkaMessageId
     */
    void updateKafkaMessage(Integer status, Long kafkaMessageId);

}
