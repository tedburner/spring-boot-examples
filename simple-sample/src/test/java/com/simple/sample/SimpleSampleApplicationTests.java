package com.simple.sample;

import com.simple.sample.event.TestEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleSampleApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        TestEvent testEvent = new TestEvent("hello", "msg");
        applicationContext.publishEvent(testEvent);

    }

}
