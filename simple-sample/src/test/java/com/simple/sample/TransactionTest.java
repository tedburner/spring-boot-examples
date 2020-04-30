package com.simple.sample;

import com.simple.sample.service.transaction.AccountAmountLogService;
import com.simple.sample.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: lingjun.jlj
 * @date: 2020/4/30 09:32
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountAmountLogService accountAmountLogService;

    @Test
    public void saveAccountTest() {
        BigDecimal money = new BigDecimal(100);
        transactionService.saveAccount(1L, money);
    }

    @Test
    public void no_transaction_exception_required() {
        BigDecimal money = new BigDecimal(10);
        //扣除用户金额
        transactionService.deductAmount(1L, money);
        //添加用户流水
        accountAmountLogService.saveAccountAmountLog(1L, money);

        throw new RuntimeException("抛出异常");
    }

    @Test
    public void no_transaction_required_exception() {
        BigDecimal money = new BigDecimal(10);
        //扣除用户金额
        transactionService.deductAmount(1L, money);
        //添加用户流水
        accountAmountLogService.saveAccountAmountLogException(1L, money);
    }

    @Test
    @Transactional
    public void transaction_exception_required_required() {
        BigDecimal money = new BigDecimal(10);
        //扣除用户金额
        transactionService.deductAmount(1L, money);
        //添加用户流水
        accountAmountLogService.saveAccountAmountLog(1L, money);

        throw new RuntimeException("抛出异常");
    }

    @Test
    @Transactional
    public void transaction_required_required_exception() {
        BigDecimal money = new BigDecimal(10);
        //扣除用户金额
        transactionService.deductAmount(1L, money);
        //添加用户流水
        accountAmountLogService.saveAccountAmountLogException(1L, money);
    }

    @Test
    @Transactional
    public void transaction_required_required_exception_try() {
        BigDecimal money = new BigDecimal(10);
        //扣除用户金额
        transactionService.deductAmount(1L, money);
        //添加用户流水
        accountAmountLogService.saveAccountAmountLog(1L, money);

        try {
            //添加用户流水
            accountAmountLogService.saveAccountAmountLogException(1L, money);
        } catch (Exception e) {
            log.info("方法回滚");
        }
    }
}
