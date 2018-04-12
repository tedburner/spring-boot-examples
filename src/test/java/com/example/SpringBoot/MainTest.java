package com.example.SpringBoot;

import com.example.SpringBoot.dto.DO.CityDO;
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
        CityDO builder = CityDO.CityBuilder.aCity()
                .withProvinceId(1L)
                .withName("金华市")
                .withDescription("金华火腿")
                .build();
        System.out.println(builder);
    }
}
