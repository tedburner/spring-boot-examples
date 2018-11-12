package com.example.springboot.persist.kafkaMessage;

import com.example.springboot.domain.DO.kafkaMessage.KafkaMessageDO;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

public interface KafkaMessageMapper {

    /**
     * 插入kafka 消息信息
     */
    int insertKafkaMessage(KafkaMessageDO kafkaMessageDO);
}
