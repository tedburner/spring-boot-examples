package com.example.springboot;

import com.example.springboot.kafka.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @data 2018/5/15
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

    @Autowired
    private Producer producer;

    @Test
    public void kafkaTest(){
        producer.send();
    }
}
