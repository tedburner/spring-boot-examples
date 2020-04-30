package com.simple.sample.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class AccountAmountLog {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private Long userId;

    private BigDecimal amount;

    @Column(insertable = false, columnDefinition = "int default 1")
    private Integer status;

    @CreatedDate
    private Date createTime;
}
