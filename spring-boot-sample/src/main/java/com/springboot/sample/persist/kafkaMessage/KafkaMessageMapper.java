package com.springboot.sample.persist.kafkaMessage;

import com.springboot.sample.domain.DO.kafkaMessage.KafkaMessageDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author kiturone
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
