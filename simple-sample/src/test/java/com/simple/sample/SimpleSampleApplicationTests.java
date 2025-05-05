package com.simple.sample;

import com.simple.sample.event.TestEvent;
import com.simple.sample.service.common.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SimpleSampleApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() {
        TestEvent testEvent = new TestEvent("hello", "msg");
        applicationContext.publishEvent(testEvent);

    }

    @Test
    public void asyncCountLatchTest(){
        testService.sayHello();
    }

}
