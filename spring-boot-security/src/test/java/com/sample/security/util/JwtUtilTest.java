package com.sample.security.util;

import com.sample.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() throws Exception {
        jwtUtil = new JwtUtil();

        // 使用反射设置SECRET_KEY和expiration，因为它们有默认值但可以通过属性配置
        Field secretField = JwtUtil.class.getDeclaredField("SECRET_KEY");
        secretField.setAccessible(true);
        secretField.set(jwtUtil, "testSecretForUnitTest");

        Field expirationField = JwtUtil.class.getDeclaredField("expiration");
        expirationField.setAccessible(true);
        expirationField.set(jwtUtil, 3600); // 1小时
    }

    @Test
    void testGenerateAndExtractUsername() {
        // 创建一个模拟用户
        Collection<? extends org.springframework.security.core.authority.SimpleGrantedAuthority> authorities =
            Arrays.asList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"));

        User user = new User("testuser", "password", authorities);

        // 生成token
        String token = jwtUtil.generateToken(user);

        // 验证token不为空
        assertNotNull(token);
        assertFalse(token.isEmpty());

        // 提取用户名
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testValidateToken() {
        Collection<? extends org.springframework.security.core.authority.SimpleGrantedAuthority> authorities =
            Arrays.asList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"));

        User user = new User("testuser", "password", authorities);

        // 生成token
        String token = jwtUtil.generateToken(user);

        // 验证token有效
        boolean isValid = jwtUtil.validateToken(token, user);
        assertTrue(isValid);
    }

    @Test
    void testExtractExpiration() {
        Collection<? extends org.springframework.security.core.authority.SimpleGrantedAuthority> authorities =
            Arrays.asList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"));

        User user = new User("testuser", "password", authorities);

        // 生成token
        String token = jwtUtil.generateToken(user);

        // 提取过期时间
        Date expiration = jwtUtil.extractExpiration(token);
        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    void testTokenExpiration() throws InterruptedException {
        // 设置一个很短的过期时间
        try {
            Field expirationField = JwtUtil.class.getDeclaredField("expiration");
            expirationField.setAccessible(true);
            expirationField.set(jwtUtil, 1); // 1秒后过期

            Collection<? extends org.springframework.security.core.authority.SimpleGrantedAuthority> authorities =
                Arrays.asList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"));

            User user = new User("testuser", "password", authorities);

            // 生成token
            String token = jwtUtil.generateToken(user);

            // 等待超过过期时间
            Thread.sleep(1500); // 等待1.5秒

            // 验证token已过期
            boolean isExpired = jwtUtil.validateToken(token, user);
            assertFalse(isExpired);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}