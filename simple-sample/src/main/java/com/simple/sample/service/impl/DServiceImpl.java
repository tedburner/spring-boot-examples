package com.simple.sample.service.impl;

import com.simple.sample.service.DService;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2019/5/15 14:03
 * @description:
 */
@Service
public class DServiceImpl implements DService {


    @Override
    public void sayD(String type) {
        System.out.println(type + " =====》》D");
    }
}
