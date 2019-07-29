package com.sample.cache.service;

import com.sample.cache.domain.User;

/**
 * @author: Arthas
 * @date: 2019-01-04 10:31
 * @description:
 */
public interface CacheService {


    /**
     * 通过用户ID查询用户信息
     *
     * @param id
     * @return
     */
    User findUserById(Long id);
}
