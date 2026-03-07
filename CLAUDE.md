# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
# Build all modules
mvn clean package

# Build skipping tests
mvn clean package -Dmaven.test.skip=true

# Build with specific profile
mvn clean package -Pproduction

# Run a single module
cd <module-name> && mvn spring-boot:run

# Run jar with profile
java -jar target/<module>-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

## Project Structure

This is a multi-module Maven project containing Spring Boot examples and integrations. Spring Boot 2.7.18 is used across all modules.

### Module Organization

**Core Infrastructure:**
- `common-kit` - Shared utilities (Redis, Gson, Jackson, Orika bean mapping, Hessian serialization)
- `spring-boot-sample-starter` - Custom Spring Boot starter example

**Data & Persistence:**
- `spring-boot-mongodb` - MongoDB with Spring Data JPA
- `spring-boot-elasticsearch` - Elasticsearch integration
- `spring-boot-fluent-mybatis` - Fluent MyBatis example
- `spring-boot-mybatis-plus` - MyBatis-Plus integration
- `spring-boot-jooq` - jOOQ integration
- `spring-boot-cache` - Spring Cache with Redis

**Messaging & Jobs:**
- `spring-boot-mq` - RabbitMQ (delay queues, priority queues, message ack), Kafka, RocketMQ
- `spring-boot-job` - Elastic-Job distributed scheduling

**Web & Security:**
- `spring-boot-webflux` - Reactive web (note: incompatible with Spring MVC)
- `spring-boot-security` - Spring Security
- `spring-boot-websocket` - WebSocket support
- `spring-boot-sample` - Main sample with controllers, services, mappers, Kafka

**Other:**
- `spring-boot-docker` - Docker image building (Jib/Dockerfile)
- `spring-boot-prometheus` - Metrics and monitoring
- `simple-sample` - Minimal example
- `langchain-example/langchain-chat` - LangChain integration

### Architecture Patterns

**Standard Layering:**
- `controller/` - REST endpoints
- `service/` & `service/impl/` - Business logic
- `persist/` or `mapper/` - Data access (MyBatis mappers)
- `domain/DO/` & `domain/DTO/` - Data objects
- `common/` - Constants, enums, annotations, AOP
- `config/` - Spring configuration classes
- `util/` - Utilities
- `filters/` & `interceptors/` - Request processing

### Key Configuration

- **Java 17** required across all modules
- **Logging**: Logback (configured in root)
- **Multi-env**: Use `-Ptest` or `-Pproduction` profiles; config in `application-test.yml` / `application-production.yml`
- **Tests**: Skipped by default (`<skipTests>true</skipTests>` in root pom)

### External Dependencies

- **Redis**: Required for cache, session, and distributed lock examples
- **RabbitMQ/Kafka**: Required for messaging modules
- **Elasticsearch**: Required for ES modules
- **MongoDB**: Required for MongoDB module
