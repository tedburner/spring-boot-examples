package com.simple.sample.repository;

import com.simple.sample.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

/**
 * @author: kiturone
 * @date: 2019/9/20 14:18
 * @description:
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUserId(Long userId);

    @Modifying
    @Query(value = "update Account set amount= ?2 where id = ?1")
    void updateAccountById(Long id, BigDecimal money);
}
