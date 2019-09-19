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
 * @date: 2019/9/19 22:17
 * @description:
 */
@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private String name;

    private Long provinceId;

    private String description;

    private Integer status;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
