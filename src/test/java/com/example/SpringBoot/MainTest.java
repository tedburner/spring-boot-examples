package com.example.SpringBoot;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @create 2017-10-23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    public MainTest() {
    }

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();
        System.out.println(mainTest.forTest());
    }

    private int forTest() {
        int i = 8;

        while(true) {
            ++i;
            if (i > 20) {
                System.out.println("end");
                return i;
            }

            System.out.println(i);
        }
    }
}
