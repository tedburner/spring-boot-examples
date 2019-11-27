package com.simple.sample.service.common;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 09:35
 * @description:
 */
public interface TestService {

    /**
     * say hello
     */
    void sayHello();

    /**
     * say hello
     */
    void sayWaitHello();
}
