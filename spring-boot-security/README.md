# Spring Boot Security with JWT

这个模块演示了Spring Boot与Spring Security的集成，并添加了JWT（JSON Web Token）认证功能。

## 功能特性

- 基于JWT的无状态认证
- 用户注册与登录
- 角色权限控制
- 密码加密
- 自定义用户DetailsService
- JWT令牌生成与验证

## API 接口

### 1. 用户认证
- **POST** `/authenticate` - 用户登录获取JWT令牌
  ```json
  {
    "username": "user",
    "password": "password"
  }
  ```

### 2. 用户注册
- **POST** `/register` - 用户注册
  ```json
  {
    "username": "newuser",
    "password": "password",
    "email": "user@example.com"
  }
  ```

### 3. 测试接口
- **GET** `/test` - 测试接口（需要认证）
- **GET** `/admin` - 管理员接口（需要管理员权限）

## 使用方法

1. 启动应用：
```bash
mvn spring-boot:run
```

2. 注册新用户：
```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123","email":"test@example.com"}'
```

3. 用户登录获取JWT令牌：
```bash
curl -X POST http://localhost:8080/authenticate \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'
```

4. 使用JWT访问受保护的资源：
```bash
curl -X GET http://localhost:8080/test \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

## 预设用户

- 用户名: `user`, 密码: `password` (普通用户)
- 用户名: `admin`, 密码: `admin` (管理员用户)

## 技术栈

- Spring Boot 2.7.18
- Spring Security
- JWT (Json Web Token)
- BCrypt 密码加密
- Lombok
- Redis (可选)

## 配置说明

JWT配置在 `application.properties` 中：
- `jwt.secret`: JWT密钥
- `jwt.expiration`: 过期时间（秒）

## 安全措施

1. 密码使用BCrypt加密存储
2. JWT令牌包含过期时间
3. 采用无状态Session管理模式
4. 请求经过JWT过滤器验证