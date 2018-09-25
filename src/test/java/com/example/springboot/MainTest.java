package com.example.springboot;


import com.example.springboot.service.common.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @create 2017-10-23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void MainTest() {
        addressService.addAddress();
    }
}
