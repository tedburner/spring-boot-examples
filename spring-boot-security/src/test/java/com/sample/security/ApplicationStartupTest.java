package com.sample.security;

/**
 * @author: kiturone
 * @date: 2026/04/03 17:27
 * @description: 应用启动测试类，验证Spring上下文能够正确加载
 */

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