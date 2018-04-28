package com.example.SpringBoot.service.factory.test;


import com.example.SpringBoot.enums.SimpleEnum;
import org.springframework.stereotype.Service;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
@Service
public class TestAServiceProcessor implements TestProcessor {

    @Override
    public int getTag() {
        return SimpleEnum.A.getCode();
    }

    @Override
    public void show() throws Exception{
        System.out.println("A方法");
    }
}
