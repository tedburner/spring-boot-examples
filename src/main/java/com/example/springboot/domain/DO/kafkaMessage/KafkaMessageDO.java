package com.example.springboot.domain.DO.kafkaMessage;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:04
 * @Description: kafka message 表状态
 */
@Data
public class KafkaMessageDO implements Serializable {

    private static final long serialVersionUID = 4130392465891507996L;

    private Long id;
    private String topic;
    private String messageBody;
    private byte[] messageByte;
    private Integer status;
    private Long version;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
