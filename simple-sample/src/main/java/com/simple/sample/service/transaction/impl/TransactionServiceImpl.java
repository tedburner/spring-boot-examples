package com.simple.sample.service.transaction.impl;

import com.simple.sample.domain.bo.UserInfoBO;
import com.simple.sample.domain.entity.Account;
import com.simple.sample.domain.entity.AccountAmountLog;
import com.simple.sample.domain.entity.User;
import com.simple.sample.repository.AccountAmountLogRepository;
import com.simple.sample.repository.AccountRepository;
import com.simple.sample.repository.UserRepository;
import com.simple.sample.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: Arthas
 * @date: 2019/9/19 22:04
 * @description:
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountAmountLogRepository accountAmountLogRepository;

    @Autowired
    public TransactionServiceImpl(UserRepository userRepository, AccountRepository accountRepository,
                                  AccountAmountLogRepository accountAmountLogRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountAmountLogRepository = accountAmountLogRepository;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public User findUserInfo() {
        return null;
    }

    @Override
    public void saveUserInfo(UserInfoBO userInfo) {
        User user = new User();
        user.setName(userInfo.getUsername());
        user.setPassword(userInfo.getPassword());
        user.setAge(userInfo.getAge());
        user.setCard(userInfo.getCard());
        user.setPhone(userInfo.getPhone());
        userRepository.save(user);

    }

    @Override
    public void saveAccount(Long userId, BigDecimal amount) {
        Account account = new Account();
        account.setId(userId);
        account.setAmount(amount);
        accountRepository.save(account);
    }

    @Override
    public void saveAccountAmountLog(Long userId, BigDecimal amount) {
        AccountAmountLog amountLog = new AccountAmountLog();
        amountLog.setId(userId);
        amountLog.setAmount(amount);
        accountAmountLogRepository.save(amountLog);
    }

    @Override
    public void deductAmount(Long userId, BigDecimal money) {
        Account account = accountRepository.findAccountByUserId(userId);
        BigDecimal amount = account.getAmount().subtract(money);
        accountRepository.updateAccountById(account.getId(), amount);
    }

    private void throwExceptionAndDelay() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("操作出现异常");
    }

    private void delayOperation() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
