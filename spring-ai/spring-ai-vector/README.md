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

### elasticsearch

## spring ai
### Spring elasticsearch vector store 固定的参数：
该实体的主要参数是下面几个：
- id：文档的唯一标识符，用于检索和管理文档。
- content：文档的内容，通常是文本、图片或其他形式的媒体。
- metadata：文档的元数据，用于存储额外的信息，如文档的创建时间、作者、标签等。
- contentType：文档的内容类型，用于指定文档的类型，如文本、图片、音频等。
- embedding：可在配置文件中自定义向量化字段，如果不配置，则默认使用embedding字段作为向量化存储字段。