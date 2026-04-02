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

# Run tests for a specific module
cd <module-name> && mvn test

# Run specific test
mvn test -Dtest=YourTestClassName
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

## Testing Guidelines

### Unit Test Coverage Requirements

When adding new functionality to any module, the following unit test coverage is required:

#### 1. Service Layer
- Test all business logic methods
- Verify input validation and error handling
- Mock external dependencies where appropriate

#### 2. Controller Layer
- Test all API endpoints
- Verify request/response handling
- Test authentication and authorization (if applicable)

#### 3. Utility Classes
- Test all public methods with various input scenarios
- Verify edge cases and error conditions
- Test utility-specific configurations if applicable

#### 4. Security Components
- Test authentication flows
- Verify authorization rules and permissions
- Test security filter functionality

#### 5. Configuration Classes
- Test configuration properties loading
- Verify conditional bean creation

### Test Structure

**Test Organization:**
- `src/test/java/` - Unit and integration tests
- Mirror main source package structure
- Separate test classes by component (services, controllers, utils, etc.)

**Test Naming Convention:**
- `{ClassName}Test.java` for unit tests of a specific class
- `{FeatureName}IntegrationTest.java` for integration tests

**Test Categories:**
- Unit tests: Fast, isolated tests of individual methods/classes
- Integration tests: Tests involving multiple components or external systems
- Mock tests: Tests using mocked dependencies

### Minimum Test Coverage Standards

- **Service Layer**: 80%+ coverage for business logic methods
- **Utility Classes**: 90%+ coverage for all public methods
- **Controllers**: Test all endpoints and response scenarios
- **Security**: Full coverage of authentication and authorization flows
- **Configuration**: Test all configuration classes for proper initialization

### Running Tests

**Best Practices:**
- Run unit tests frequently during development (`mvn test`)
- Use specific test execution when developing (`mvn test -Dtest=TestClass`)
- Verify test coverage before committing new functionality
