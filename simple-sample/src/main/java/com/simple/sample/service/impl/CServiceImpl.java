package com.simple.sample.service.impl;

import com.simple.sample.service.AService;
import com.simple.sample.service.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:04
 * @description:
 */
@Service
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
