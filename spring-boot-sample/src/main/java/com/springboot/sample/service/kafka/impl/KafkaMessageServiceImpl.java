package com.springboot.sample.service.kafka.impl;

import com.springboot.sample.persist.kafkaMessage.KafkaMessageMapper;
import com.springboot.sample.service.kafka.KafkaMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:58
 * @Description:
 */
@Service
public class KafkaMessageServiceImpl implements KafkaMessageService {

    @Resource
    private KafkaMessageMapper kafkaMessageMapper;


    @Override
    public void updateKafkaMessage(Integer status, Long kafkaMessageId) {
        kafkaMessageMapper.update(kafkaMessageId, status);
    }
}
