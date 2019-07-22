package com.sample.mq.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/22 16:16
 * @version：1.0.0
 * @description:
 */
@Data
@Builder
public class Message {
    private Long id;

    private String msg;

    private Date sendTime;
}
