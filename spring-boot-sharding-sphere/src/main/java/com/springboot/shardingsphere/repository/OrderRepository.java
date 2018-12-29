package com.springboot.shardingsphere.repository;

import com.springboot.shardingsphere.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Arthas
 * @date: 2018-12-28 14:36
 * @description:
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
