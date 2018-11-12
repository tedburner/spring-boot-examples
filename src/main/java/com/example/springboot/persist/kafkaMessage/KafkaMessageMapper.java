package com.example.springboot.persist.kafkaMessage;

import com.example.springboot.domain.DO.kafkaMessage.KafkaMessageDO;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

public interface KafkaMessageMapper {

    int insertKafkaMessage(KafkaMessageDO kafkaMessageDO);
}
