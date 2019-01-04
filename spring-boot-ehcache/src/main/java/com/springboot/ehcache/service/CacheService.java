package com.springboot.ehcache.service;

import com.springboot.ehcache.domain.User;

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
     */
    User findUserById(Long id);
}
