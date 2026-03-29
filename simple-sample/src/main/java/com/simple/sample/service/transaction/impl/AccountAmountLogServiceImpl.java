package com.simple.sample.service.transaction.impl;

import com.simple.sample.domain.entity.AccountAmountLog;
import com.simple.sample.repository.AccountAmountLogRepository;
import com.simple.sample.service.transaction.AccountAmountLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: kiturone
 * @date: 2020/4/30 09:29
 * @description:
 */
@Slf4j
@Service
public class AccountAmountLogServiceImpl implements AccountAmountLogService {

    private final AccountAmountLogRepository accountAmountLogRepository;

    @Autowired
    public AccountAmountLogServiceImpl(AccountAmountLogRepository accountAmountLogRepository) {
        this.accountAmountLogRepository = accountAmountLogRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveAccountAmountLog(Long userId, BigDecimal amount) {
        AccountAmountLog amountLog = new AccountAmountLog();
        amountLog.setUserId(userId);
        amountLog.setAmount(amount);
        accountAmountLogRepository.save(amountLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void saveAccountAmountLogException(Long userId, BigDecimal amount) {
        AccountAmountLog amountLog = new AccountAmountLog();
        amountLog.setUserId(userId);
        amountLog.setAmount(amount);
        accountAmountLogRepository.save(amountLog);

        throw new RuntimeException("插入用户记账流水的时候，抛出异常...");
    }
}
