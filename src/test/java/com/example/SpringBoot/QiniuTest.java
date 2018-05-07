package com.example.SpringBoot;

import com.example.SpringBoot.service.image.ImageUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @date 2017-12-24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QiniuTest {
    @Autowired
    private ImageUploadService imageUploadService;

    @Test
    public void QiniuTest(){

        String base64Code = "";

        //imageUploadService.


    }
}
