package com.simple.sample.service.common;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 10:18
 * @description:
 */
public interface AsyncMethodService {

    /**
     * 异步say hello
     */
    void sayHelloAsync();

    /**
     * 异步say hello
     *
     * @param latch
     */
    void sayHelloAsync(CountDownLatch latch);
}
