package com.example.springboot;

import com.example.springboot.model.DTO.SimpleDTO;
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
        SimpleDTO builder = SimpleDTO.SimpleDTOBuilder.aSimpleDTO()
                .withId(1)
                .withName("金华市")
                .build();
        System.out.println(builder);
    }
}
