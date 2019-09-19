package com.simple.sample.service.transaction;

import com.simple.sample.domain.entity.User;

/**
 * @author: Arthas
 * @date: 2019/9/19 22:04
 * @description: 事务隔离级别演示
 */
public interface TransactionService {

    /**
     * 获取用户信息
     */
    User findUserInfo();

    /**
     * 添加
     * */
    void save(User user);
}
