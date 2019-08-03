package com.simple.sample.service.di.impl;

import com.simple.sample.service.di.DService;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:03
 * @description:
 */
//@Service
public class DServiceImpl implements DService {


    @Override
    public void sayD(String type) {
        System.out.println(type + " =====》》D");
    }
}
