package com.sample.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationStartupTest {

    @Test
    void contextLoads() {
        // 验证应用上下文能够成功加载所有必要的Bean
        assertTrue(true, "Spring上下文应该能够成功加载");
    }
}