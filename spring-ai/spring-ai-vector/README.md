## 基于DDD项目框架
```bash
src/main/java/com/ai/knowledge/vector
├── application    # 应用层：编排领域层组件，处理用例逻辑（如事务管理、事件发布）
│   ├── OrderApplicationService.java
│   └── dto/OrderDTO.java
├── domain    # 领域层：承载核心业务逻辑，包含实体、值对象、聚合根、领域服务和仓储接口，
│   ├── model/Order.java  # 领域模型（实体、值对象、聚合根）
│   ├── repository/OrderRepository.java  # 仓储接口（定义数据操作）
│   └── event/OrderPlacedEvent.java  # 领域服务（复杂业务逻辑）
├── infrastructure   # 基础设施层：实现技术细节，数据库访问、消息队列
│   ├── repository/JpaOrderRepository.java  # 仓储实现（如 JPA、MyBatis）
│   └── config/RedisConfig.java  # Spring 配置类
│   └── adapter/PayAdapter.java  # 第三方服务适配（如支付网关）
└── interfaces   # 接口层：处理外部请求（REST API、RPC），转换DTO与领域模型
    ├── controller/OrderController.java
    └── vo/OrderVO.java
```
## 向量模型
向量化模型不需要启动，只需要拉取模型即可。
### nomic-embed-text
向量纬度：768

### bge-m3
向量纬度：1024


## 向量数据库
### chromadb
```bash
docker run -d --name chromadb -p 8001:8000 -v D:\Document\docker\chroma:/chroma/chroma -e IS_PERSISTENT=TRUE -e ANONYMIZED_TELEMETRY=TRUE chromadb/chroma
```