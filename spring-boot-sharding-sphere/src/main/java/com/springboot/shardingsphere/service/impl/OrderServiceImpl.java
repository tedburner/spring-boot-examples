package com.springboot.shardingsphere.service.impl;

import com.springboot.shardingsphere.domain.Order;
import com.springboot.shardingsphere.repository.OrderRepository;
import com.springboot.shardingsphere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Arthas
 * @date: 2018-12-28 14:38
 * @description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void batchSaveOrder(List<Order> orderList) {
        orderRepository.saveAll(orderList);
    }

    @Override
    public List<Order> findOrderList() {
        return orderRepository.findAll();
    }
}
