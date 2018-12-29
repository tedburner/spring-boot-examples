package com.springboot.shardingsphere.service;

import com.springboot.shardingsphere.domain.Order;

import java.util.List;

/**
 * @author: Arthas
 * @date: 2018-12-28 14:38
 * @description:
 */
public interface OrderService {

    /**
     * 添加订单
     */
    void saveOrder(Order order);

    /**
     * 批量插入订单号
     */
    void batchSaveOrder(List<Order> orderList);

    /**
     * 查询订单
     */
    List<Order> findOrderList();
}
