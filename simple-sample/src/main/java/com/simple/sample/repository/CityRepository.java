package com.simple.sample.repository;

import com.simple.sample.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Arthas
 * @date: 2019/9/19 22:18
 * @description:
 */
public interface CityRepository extends JpaRepository<City, Long> {
}
