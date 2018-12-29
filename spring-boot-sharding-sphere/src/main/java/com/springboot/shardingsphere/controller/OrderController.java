package com.springboot.shardingsphere.controller;

import com.google.common.collect.Lists;
import com.springboot.shardingsphere.domain.Order;
import com.springboot.shardingsphere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Arthas
 * @date: 2018-12-28 14:37
 * @description:
 */
@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("query")
    public Object queryAll() {
        return orderService.findOrderList();
    }

    @RequestMapping("/create")
    public Object add() {
        List<Order> orders = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            orders.add(order);
        }
        orderService.batchSaveOrder(orders);

        orders.clear();
        for (int i = 10; i < 20; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orders.add(order);
        }
        orderService.batchSaveOrder(orders);

        orders.clear();
        for (int i = 20; i < 30; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orders.add(order);
        }
        orderService.batchSaveOrder(orders);

        return "success";
    }

}
