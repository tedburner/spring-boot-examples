package com.example.springboot;


import com.example.springboot.service.kafka.KafkaMessageService;
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
    private KafkaMessageService kafkaMessageService;

    @Test
    public void kafkaTest() {
        kafkaMessageService.sendMessage("test", "这是一条测试消息！是由spring boot推送的一条kafka消息！");
    }
}
