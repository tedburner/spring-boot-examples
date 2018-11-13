package com.example.springboot.domain.DTO.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:34
 * @description:
 */
@Data
public class MessageDTO implements Serializable {

    private String sender;

    private String receiver;

    private String message;

    /**
     * 模板code
     */
    private String templateCode;

    private Long kafkaMessageId;
}
