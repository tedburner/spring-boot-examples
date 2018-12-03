package com.sample.springboot.persist.kafkaMessage;

import com.sample.springboot.domain.DO.kafkaMessage.KafkaMessageDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

public interface KafkaMessageMapper {

    /**
     * 插入kafka 消息信息
     */
    int insertKafkaMessage(KafkaMessageDO kafkaMessageDO);

    /**
     * 修改kafka消息状态
     *
     * @param kafkaMessageId
     * @param status
     */
    void update(@Param("id") Long kafkaMessageId, @Param("status") Integer status);
}
