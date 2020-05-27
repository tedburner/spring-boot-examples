package com.sample.cache.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/31 10:26
 * @version：1.0.0
 * @description:
 */
@Data
public class Task implements Serializable {

    /**
     * 任务id
     */
    private String id;
    /**
     * 任务执行时间
     */
    private long time;
    /**
     * 描述
     */
    private String desc;
}
