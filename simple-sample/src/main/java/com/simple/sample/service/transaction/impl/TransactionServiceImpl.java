package com.simple.sample.service.transaction.impl;

import com.simple.sample.domain.bo.UserInfoBO;
import com.simple.sample.domain.entity.Account;
import com.simple.sample.domain.entity.User;
import com.simple.sample.repository.AccountRepository;
import com.simple.sample.repository.UserRepository;
import com.simple.sample.service.transaction.AccountAmountLogService;
import com.simple.sample.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: kiturone
 * @date: 2019/9/19 22:04
 * @description:
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountAmountLogService accountAmountLogService;

    @Autowired
    public TransactionServiceImpl(UserRepository userRepository, AccountRepository accountRepository,
                                  AccountAmountLogService accountAmountLogService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountAmountLogService = accountAmountLogService;
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
        account.setUserId(userId);
        account.setAmount(amount);
        accountRepository.save(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void deductAmount(Long userId, BigDecimal money) {
        Account account = accountRepository.findAccountByUserId(userId);
        BigDecimal amount = account.getAmount().subtract(money);
        accountRepository.updateAccountById(account.getId(), amount);
    }
}
