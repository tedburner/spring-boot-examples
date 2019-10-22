package com.simple.sample.service.common;

import com.simple.sample.domain.entity.User;

import java.util.List;

/**
 * @author: Arthas
 * @date: 2019-08-03 22:58
 * @description:
 */
public interface UserService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户ID查询用户信息
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 需要批量插入用户数据
     *
     * @param num 需要插入的用户量
     */
    void batchSave(Integer num);
}
