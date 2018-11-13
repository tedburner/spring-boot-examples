package com.example.springboot.domain.DTO.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:34
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SMSMessageDTO implements Serializable {

    /**
     * 接收人手机号码
     */
    private String phone;

    /**
     * 模板code
     */
    private String templateCode;

    private Long kafkaMessageId;

    /**
     * 模板参数
     */
    private String templateParam;
}
