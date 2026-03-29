package com.example.jooq.service;

import com.example.jooq.domain.UserInfo;

/**
 * @author: kiturone
 * @date: 2022/3/22 22:23
 * @description:
 */
public interface UserService {

    UserInfo findById(Long id);
}
