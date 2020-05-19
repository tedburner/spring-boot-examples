package com.simple.sample.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/19 22:14
 * @description:
 */
@Data
@Entity
@Table(name = "province")
@EntityListeners(AuditingEntityListener.class)
public class Province {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private String name;

    @Column(insertable = false, columnDefinition = "int default 1")
    private Integer status;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
