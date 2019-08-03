package com.simple.sample.service.di.impl;

import com.simple.sample.service.di.AService;
import com.simple.sample.service.di.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:04
 * @description:
 */
//@Service
public class CServiceImpl implements CService {

    private final AService aService;

    @Autowired
    @Lazy
    public CServiceImpl(AService aService) {
        this.aService = aService;
    }

    @Override
    public void sayC(String type) {
        System.out.println(type + "========>>>>>C");
        aService.sayA("C");
    }
}
