package com.springboot.shardingsphere.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * @author: Arthas
 * @date: 2018-12-28 14:31
 * @description:
 */
@Data
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    private Long orderId;

    private Long userId;
}
