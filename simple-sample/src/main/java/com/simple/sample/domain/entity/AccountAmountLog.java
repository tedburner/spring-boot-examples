package com.simple.sample.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/20 14:07
 * @description:
 */
@Data
@Entity
@Table(name = "account_amount_log")
public class AccountAmountLog {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private Long userId;

    private BigDecimal amount;

    private String status;

    @CreatedDate
    private Date createTime;
}
