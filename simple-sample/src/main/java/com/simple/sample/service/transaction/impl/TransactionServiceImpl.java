package com.simple.sample.service.transaction.impl;

import com.simple.sample.domain.entity.User;
import com.simple.sample.service.transaction.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Arthas
 * @date: 2019/9/19 22:04
 * @description:
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public User findUserInfo() {
        return null;
    }

    @Override
    public void save(User user) {

    }
}
