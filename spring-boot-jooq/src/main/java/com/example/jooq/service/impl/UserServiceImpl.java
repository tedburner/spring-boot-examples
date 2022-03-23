package com.example.jooq.service.impl;

import com.example.jooq.domain.UserInfo;
import com.example.jooq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/22 22:23
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private DSLContext dslContext;

    @Override
    public UserInfo findById(Long id) {
        return null;
    }
}
