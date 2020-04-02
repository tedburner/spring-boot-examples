package com.simple.sample.service.common;

import com.simple.sample.domain.bo.UserInfoBO;
import com.simple.sample.domain.dto.UserDTO;

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

    /**
     * 测试Aop
     *
     * @param userDTO
     */
    void aspectCase(UserDTO userDTO);
}
