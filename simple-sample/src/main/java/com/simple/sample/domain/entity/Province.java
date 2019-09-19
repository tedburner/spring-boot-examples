package com.simple.sample.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: Arthas
 * @date: 2019/9/19 22:14
 * @description:
 */
@Data
@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private String name;

    private Integer status;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
