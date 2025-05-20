package com.simple.sample.service.transaction;

import java.math.BigDecimal;

/**
 * @author: kiturone
 * @date: 2020/4/30 09:29
 * @description:
 */
public interface AccountAmountLogService {

    /**
     * 添加消费流水
     *
     * @param userId
     * @param amount
     */
    void saveAccountAmountLog(Long userId, BigDecimal amount);

    /**
     * 添加消费流水
     *
     * @param userId
     * @param amount
     */
    void saveAccountAmountLogException(Long userId, BigDecimal amount);
}
