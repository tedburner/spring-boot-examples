package com.simple.sample.service.di.impl;

import com.simple.sample.service.di.AService;
import com.simple.sample.service.di.BService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:03
 * @description:
 */
//@Service
public class AServiceImpl implements AService {

    private final BService bService;

    @Autowired
    public AServiceImpl(BService bService) {
        this.bService = bService;
    }


    @Override
    public void sayA(String type) {
        System.out.println(type + " =====》》A");
        bService.sayB("A");
    }
}
