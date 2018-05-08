package com.example.springboot.service.factory.test;


import com.example.springboot.enums.SimpleEnum;
import org.springframework.stereotype.Service;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
@Service
public class TestBServiceProcessor implements TestProcessor {

    @Override
    public int getTag() {
        return SimpleEnum.B.getCode();
    }

    @Override
    public void show() throws Exception{
        System.out.println("B方法");
    }
}
