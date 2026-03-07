# SpringBoot

Spring Boot 项目。配置了 mybatis+redis。
- 使用了 pagehelper 的分页插件
- 日志使用 logback
- 使用 maven 实现开发/测试环境多环境

在 idea 中使用 mybatis，推荐一个插件—— Free Mybatis plugin，
感觉挺好用的（声明：不是本人开发的插件，只是使用中感觉不错）。

## 目录结构

```
spring-boot-examples/
├── common-kit                 # 通用工具包（Redis、Gson、Jackson、Orika、Hessian）
├── simple-sample              # 简单示例
├── spring-boot-sample         # 主示例项目
├── spring-boot-sample-starter # 自定义 Starter 示例
├── spring-boot-mq             # 消息队列（RabbitMQ、Kafka、RocketMQ）
├── spring-boot-job            # 分布式任务调度（Elastic-Job）
├── spring-boot-cache          # 缓存（Spring Cache + Redis）
├── spring-boot-mongodb        # MongoDB
├── spring-boot-elasticsearch  # Elasticsearch
├── spring-boot-webflux        # 响应式 Web（WebFlux）
├── spring-boot-security       # Spring Security
├── spring-boot-websocket      # WebSocket
├── spring-boot-docker         # Docker 镜像构建
├── spring-boot-prometheus     # Prometheus 监控
├── spring-boot-jooq           # jOOQ
├── spring-boot-mybatis-plus   # MyBatis-Plus
├── spring-boot-fluent-mybatis # Fluent MyBatis
└── langchain-example          # LangChain 示例
```

## 快速开始

### 项目打包

```bash
# 进入项目根目录
cd spring-boot-examples

# 打包所有模块
mvn clean package

# 跳过测试打包
mvn clean package -Dmaven.test.skip=true

# 根据环境打包
mvn clean package -Pproduction
```

### 启动应用

```bash
java -jar target/<模块名>-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

## 模块说明

### 消息队列（spring-boot-mq）

**RabbitMQ**
- 普通队列
- 死信队列（延迟队列）
- 优先级队列
- 消息确认机制（confirm/return）
- 消息持久化

**Kafka**
- 生产者/消费者示例
- 消息可靠性投递

**RocketMQ**
- 基本使用示例

### Redis（common-kit）

项目整合了 Redis，使用 Spring Boot 2.0+ 配置方式。

实现 Redis 分片，使用 `ShardedJedisPool` 实现分片。[详情](/common-kit/src/main/java/com/kit/common/cache)

**Redis vs Memcached**
| 特性 | Redis | Memcached |
|------|-------|-----------|
| 线程模型 | 单线程 I/O 复用 | 多线程 |
| 数据结构 | String/List/Hash/Set/ZSet | 简单 k/v |
| 持久化 | 支持 RDB/AOF | 不支持 |
| 大数据性能 | 较好 | 100k+ 数据更优 |

### Docker

**构建方式**
- [使用 pom.xml 直接构建](/spring-boot-sample)
- [使用 Dockerfile 构建](/spring-boot-docker)

**Jib**
Jib 是谷歌的容器工具，不需要写 Dockerfile 就可以直接构建镜像。
- [官网 GitHub](https://github.com/GoogleContainerTools/jib)

### 多环境配置

使用 `profiles` 实现环境分离：
- `application-test.yml` - 测试环境
- `application-production.yml` - 生产环境

```bash
# 启动时指定环境
java -jar app.jar --spring.profiles.active=production
```

### 数据库

**MongoDB** - [详情](/spring-boot-mongodb)

使用 Spring Data JPA 进行操作，满足基本项目需求。

**Elasticsearch** - [详情](/spring-boot-elasticsearch)

**MyBatis 系列**
- [MyBatis-Plus](/spring-boot-mybatis-plus)
- [Fluent MyBatis](/spring-boot-fluent-mybatis)
- [jOOQ](/spring-boot-jooq)

### 分布式任务调度（spring-boot-job）

使用 Elastic-Job 实现分布式调度。
- [Elastic-Job-Lite](https://github.com/elasticjob/elastic-job-lite) - 轻量级无中心化方案
- [Elastic-Job-Cloud](https://github.com/elasticjob/elastic-job-cloud) - Mesos Framework 方案

### Web 相关

**Spring WebFlux** - [详情](/spring-boot-webflux)

响应式编程模型，注意：WebFlux 不兼容 Spring MVC。

**Spring Security** - 安全认证

**WebSocket** - 实时通信

### 其他

**数据库分片**
- [Sharding-Sphere](https://github.com/tedburner/sharding-sphere)

**LangChain** - [langchain-example/langchain-chat](/langchain-example/langchain-chat)

## 技术栈

| 技术 | 版本 |
|------|------|
| Java | 17 |
| Spring Boot | 2.7.18 |
| MyBatis | ✓ |
| Redis | ✓ |
| Lombok | ✓ |

## 参考资源

- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [Spring Boot Guides](https://spring.io/guides)
- [MyBatis](https://mybatis.org/mybatis-3/)
- [Redis](https://redis.io/)
