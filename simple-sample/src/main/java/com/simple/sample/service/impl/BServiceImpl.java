package com.simple.sample.service.impl;

import com.simple.sample.service.BService;
import com.simple.sample.service.CService;
import com.simple.sample.service.DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:03
 * @description:
 */
@Service
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
