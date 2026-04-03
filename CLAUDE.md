# CLAUDE.md

本文档为 Claude Code（claude.ai/code）在本项目中工作时提供指导。

## 构建与测试命令

```bash
# 构建所有模块
mvn clean package

# 构建并跳过测试
mvn clean package -Dmaven.test.skip=true

# 使用指定配置构建
mvn clean package -Pproduction

# 运行单个模块
cd <module-name> && mvn spring-boot:run

# 使用配置运行jar包
java -jar target/<module>-0.0.1-SNAPSHOT.jar --spring.profiles.active=test

# 运行特定模块的测试
cd <module-name> && mvn test

# 运行特定测试类
mvn test -Dtest=YourTestClassName
```

## 项目结构

这是一个多模块Maven项目，包含Spring Boot示例和集成功能。Spring Boot版本统一使用2.7.18。

### 模块组织

**核心基础设施：**
- `common-kit` - 通用工具包（Redis、Gson、Jackson、Orika bean映射、Hessian序列化）
- `spring-boot-sample-starter` - 自定义Spring Boot starter示例

**数据与持久化：**
- `spring-boot-mongodb` - MongoDB与Spring Data JPA
- `spring-boot-elasticsearch` - Elasticsearch集成
- `spring-boot-fluent-mybatis` - Fluent MyBatis示例
- `spring-boot-mybatis-plus` - MyBatis-Plus集成
- `spring-boot-jooq` - jOOQ集成
- `spring-boot-cache` - Spring Cache与Redis

**消息队列与定时任务：**
- `spring-boot-mq` - RabbitMQ（延迟队列、优先级队列、消息确认）、Kafka、RocketMQ
- `spring-boot-job` - Elastic-Job分布式调度

**Web与安全：**
- `spring-boot-webflux` - 响应式Web（注意：与Spring MVC不兼容）
- `spring-boot-security` - Spring Security安全认证
- `spring-boot-websocket` - WebSocket支持
- `spring-boot-sample` - 主示例项目，包含controllers、services、mappers、Kafka

**其他：**
- `spring-boot-docker` - Docker镜像构建（Jib/Dockerfile）
- `spring-boot-prometheus` - 监控与指标
- `simple-sample` - 最小示例

### 架构模式

**标准分层结构：**
- `controller/` - REST接口
- `service/` 与 `service/impl/` - 业务逻辑
- `persist/` 或 `mapper/` - 数据访问（MyBatis mappers）
- `domain/DO/` 与 `domain/DTO/` - 数据对象
- `common/` - 常量、枚举、注解、AOP
- `config/` - Spring配置类
- `util/` - 工具类
- `filters/` 与 `interceptors/` - 请求处理

### 关键配置

- **Java版本**：所有模块需要Java 17
- **日志**：Logback（根目录配置）
- **多环境**：使用 `-Ptest` 或 `-Pproduction` 配置；配置文件位于 `application-test.yml` / `application-production.yml`
- **测试**：默认跳过（根目录pom中 `<skipTests>true</skipTests>`）

### 外部依赖

- **Redis**：缓存、会话和分布式锁示例需要
- **RabbitMQ/Kafka**：消息队列模块需要
- **Elasticsearch**：ES模块需要
- **MongoDB**：MongoDB模块需要

## 测试指南

### 单元测试覆盖要求

为任何模块添加新功能时，需要满足以下单元测试覆盖要求：

#### 1. Service层
- 测试所有业务逻辑方法
- 验证输入校验和错误处理
- 适当模拟外部依赖

#### 2. Controller层
- 测试所有API接口
- 验证请求/响应处理
- 测试认证和授权（如适用）

#### 3. 工具类
- 使用各种输入场景测试所有公共方法
- 验证边界条件和错误情况
- 测试工具类特定配置（如适用）

#### 4. 安全组件
- 测试认证流程
- 验证授权规则和权限
- 测试安全过滤器功能

#### 5. 配置类
- 测试配置属性加载
- 验证条件化Bean创建

### 测试结构

**测试组织：**
- `src/test/java/` - 单元测试和集成测试
- 镜像主源代码包结构
- 按组件（services、controllers、utils等）分离测试类

**测试命名规范：**
- `{ClassName}Test.java` - 针对特定类的单元测试
- `{FeatureName}IntegrationTest.java` - 集成测试

**测试分类：**
- 单元测试：快速、隔离的单个方法/类测试
- 集成测试：涉及多个组件或外部系统的测试
- Mock测试：使用模拟依赖的测试

### 最低测试覆盖标准

- **Service层**：业务逻辑方法覆盖率80%+
- **工具类**：所有公共方法覆盖率90%+
- **Controllers**：测试所有端点和响应场景
- **Security**：完整的认证授权流程测试
- **Configuration**：测试所有配置类的正确初始化

### 运行测试

**最佳实践：**
- 开发过程中频繁运行单元测试（`mvn test`）
- 开发时使用特定测试执行（`mvn test -Dtest=TestClass`）
- 提交新功能前验证测试覆盖率

## 代码规范

### 文件头注释

创建新的Java文件时，必须添加以下头注释（实际创建时替换日期和时间）：

```java
/**
 * @author: kiturone
 * @date: ${DATE} ${TIME}
 * @description: 文件描述内容
 */
```