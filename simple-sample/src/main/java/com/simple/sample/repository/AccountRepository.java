package com.simple.sample.repository;

import com.simple.sample.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/20 14:18
 * @description:
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUserId(Long userId);

    @Modifying
    @Query(value = "update Account set amount= :money where id =: id", nativeQuery = true)
    void updateAccountById(@Param("id") Long id, @Param("money") BigDecimal money);
}
