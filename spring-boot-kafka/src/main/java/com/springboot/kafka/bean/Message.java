package com.springboot.kafka.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2019/4/11 19:09
 * @description:
 */
@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}
