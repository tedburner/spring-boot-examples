package com.simple.sample.service.impl;

import com.simple.sample.service.AService;
import com.simple.sample.service.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:03
 * @description:
 */
@Service
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
