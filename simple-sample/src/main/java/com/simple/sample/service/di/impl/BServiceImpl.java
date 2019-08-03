package com.simple.sample.service.di.impl;

import com.simple.sample.service.di.BService;
import com.simple.sample.service.di.CService;
import com.simple.sample.service.di.DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:03
 * @description:
 */
//@Service
public class BServiceImpl implements BService {

    private final CService cService;
    private final DService dService;

    @Autowired
    @Lazy
    public BServiceImpl(CService cService, DService dService) {
        this.cService = cService;
        this.dService = dService;
    }


    @Override
    public void sayB(String type) {
        System.out.println(type + "=========>>>>B");
        dService.sayD("B");
        cService.sayC("C");
    }
}
