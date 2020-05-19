package com.simple.sample.service.transaction;

import com.simple.sample.domain.bo.UserInfoBO;
import com.simple.sample.domain.entity.User;

import java.math.BigDecimal;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/19 22:04
 * @description: 事务隔离级别演示
 */
public interface TransactionService {

    /**
     * 获取用户信息
     */
    User findUserInfo();

    /**
     * 添加用户信息
     *
     * @param user
     */
    void saveUserInfo(UserInfoBO user);

    /**
     * 添加用户账号
     *
     * @param userId
     * @param amount
     */
    void saveAccount(Long userId, BigDecimal amount);

    /**
     * 扣除用户金额
     *
     * @param userId
     * @param money
     */
    void deductAmount(Long userId, BigDecimal money);
}
