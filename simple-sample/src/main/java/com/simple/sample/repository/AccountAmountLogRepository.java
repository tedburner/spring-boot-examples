package com.simple.sample.repository;

import com.simple.sample.domain.entity.AccountAmountLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/20 14:18
 * @description:
 */
public interface AccountAmountLogRepository extends JpaRepository<AccountAmountLog, Long> {
}
