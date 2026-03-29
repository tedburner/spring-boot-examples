package com.sample.cache.service;

import com.sample.cache.domain.Task;

/**
 * @author: kiturone
 * @date: 2019/7/31 10:25
 * @version：1.0.0
 * @description:
 */
public interface RedisService {

    /**
     * 把任务添加到队列中
     */
    void addDelayQueue(Task task);
}
