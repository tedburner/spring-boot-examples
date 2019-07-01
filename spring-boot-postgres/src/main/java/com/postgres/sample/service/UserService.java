package com.postgres.sample.service;

import com.postgres.sample.domain.UserDO;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/1 16:57
 * @version：1.0
 * @description:
 */
public interface UserService {

    /**
     * 插入用户信息
     */
    void save(UserDO userDO);

    /**
     * 查询用户信息
     */
    List<UserDO> findUserList();
}
