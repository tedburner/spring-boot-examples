package com.sample.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.security.controller.AuthController;
import com.sample.security.entity.User;
import com.sample.security.service.impl.UserServiceImpl;
import com.sample.security.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
class SecurityModuleTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserServiceImpl userDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAuthenticateEndpoint() throws Exception {
        // 模拟认证请求
        AuthController.AuthRequest authRequest = new AuthController.AuthRequest();
        authRequest.setUsername("user");
        authRequest.setPassword("password");

        // 模拟UserDetails
        User mockUser = new User("user", "{noop}password", null);

        when(userDetailsService.loadUserByUsername("user")).thenReturn(mockUser);
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn("mock.jwt.token");

        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.user").exists());
    }

    @Test
    void testRegisterEndpoint() throws Exception {
        AuthController.RegisterRequest registerRequest = new AuthController.RegisterRequest();
        registerRequest.setUsername("newuser");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newuser@example.com");

        User mockUser = new User("newuser", "{noop}password", null);
        mockUser.setEmail("newuser@example.com");

        when(userDetailsService.registerUser(anyString(), anyString(), anyString())).thenReturn(mockUser);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"))
                .andExpect(jsonPath("$.user").exists());
    }

    @Test
    void testUnauthenticatedAccessToProtectedResource() throws Exception {
        mockMvc.perform(post("/test"))
                .andExpect(status().isUnauthorized()); // 未认证访问受保护资源应该返回401
    }

    @Test
    void testContextLoads() {
        // 确保Spring上下文能够正确加载所有Bean
    }
}