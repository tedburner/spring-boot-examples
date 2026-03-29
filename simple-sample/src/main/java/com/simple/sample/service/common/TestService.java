package com.simple.sample.service.common;

import com.simple.sample.domain.dto.UserDTO;
import com.simple.sample.util.http.NewResponseModel;

/**
 * @author: kiturone
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

    /**
     * 测试Aop
     *
     * @param userDTO
     */
    void aspectCase(UserDTO userDTO);

    /**
     * 测试synchronized能否锁住字符串
     *
     * @param str
     */
    void testSynchronizedChar(String str);

    /**
     * 捕捉异常
     *
     * @return
     */
    NewResponseModel tryCatchException();
}
