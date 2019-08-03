package com.springboot.sample.domain.DO.kafkaMessage;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: lingjun.jlj
 * @date: 2018/8/9 19:17
 * @description:
 */
@Data
public class MessageTemplateDO implements Serializable {

    private static final long serialVersionUID = -385012250108255939L;

    private Long id;
    private String templateCode;
    private String content;
    private Integer type;
    private Integer status;

    private Long kafkaMessageId;
}
