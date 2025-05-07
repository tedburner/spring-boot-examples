# SpringBoot
Spring Boot项目。配置了 mybatis+redis。
- 使用了pagehelper的分页插件
- 日志使用logback。
- 使用maven实现开发/测试环境多环境

在idea中使用mybatis，推荐一个插件—— Free Mybatis plugin，
感觉挺好用的（声明：不是本人开发的插件，只是使用中感觉不错）。
- [Spring AI 如何接入大模型](#Spring Ai)
- [项目打包](#项目打包)
- [Redis](#redis)
- [Mq](#mq)
- [Docker](#docker)
- [项目环境分离](#测试环境/正式环境)
- [MongoDB](#mongodb)
- [elasticsearch](#elasticsearch)
- [Elastic Job](#elastic-job)
- [sharing sphere](#sharing-sphere)
- [canal](#canal)
- [Reactive(Spring WebFlux)](#reactive)

## 项目打包
### 打成jar包
```bash
cd 项目跟目录（和pom.xml同级）
mvn clean package
## 或者执行下面的命令
## 排除测试代码后进行打包
mvn clean package  -Dmaven.test.skip=true
## 如果想根据环境打不同的jar
mvn clean package -Pproduction
```

### 打包时跳过测试
```bash
     <plugin>
         <groupId>org.apache.maven.plugins</groupId>
         <artifactId>maven-surefire-plugin</artifactId>
         <version>2.18.1</version>
         <configuration>
        <!--true跳过测试-->
               <skipTests>true</skipTests>
         </configuration>
      </plugin>
```

### 启动jar

```bash
java -jar  target/项目名称-版本号.jar --spring.profiles.active=test/production
```

## MQ
### RabbitMq
[RabbitMQ整合](/spring-boot-mq)
- 实现普通队列
- 通过死信队列实现了延迟队列
- 优先级队列

### kafka
代码已经整合到 master 上面     
除了sample上之外，还在[kafka moudle](/spring-boot-mq)

### RocketMQ
[RocketMq 和Spring Boot整合](/spring-boot-mq)

## Redis
项目整合了Redis，使用spring boot2.0。springboot2.0之后Redis
配置和之前的有点区别。

实现 Redis 分片，使用 Redis ShardedJedisPool 实现 Redis 的分片。[详情](/common-kit/src/main/java/com/kit/common/cache)

### Redis 和 memcached
- MemCached 是多线程，Redis使用单线程的I/O复用模型。而在
100k以上的数据中，Memcached性能要高于Redis，虽然Redis最
近也在存储大数据的性能上进行优化，但是比起Memcached，还是稍有逊色。
- Redis和MemCached都是将数据存放在内存中。不过MemCached还可
用于图片、视频等。Redis 除了支持简单的k/v类型的数据外，还支持
list、hash、set等数据结构的存储
- 存储数据安全：MemCached如果挂了，数据就丢了；Redis会定期保存
到磁盘中（持久化）
- 内存使用效率：使用简单的k-v存储，MemCached的内存利用率更
高，而如果Redis采用hash结构做k-v存储，由于其组合式的压缩，其
内存利用率高于MemCached.    

整合MemCached —— [Spring Boot 2.0(八)：Spring Boot 集成 Memcached](http://www.ityouknow.com/springboot/2018/09/01/spring-boot-memcached.html)

## docker
构建 docker 镜像。
- [pom.xml 直接构建](/spring-boot-sample)
- [Dockerfile 构建镜像](/spring-boot-docker)

## 测试环境/正式环境
使用profiles实现了环境分开

## MongoDB
整合了 MongoDB，使用 Spring 的jpa进行数据库的操作，基本满足正常
项目需求。

[地址](spring-boot-mongodb)

## elasticsearch
项目 [地址](spring-boot-elasticsearch)

## Elastic Job
项目[地址](spring-boot-job)

## sharing sphere 数据库分片
[地址](https://github.com/tedburner/sharding-sphere)

## Reactive
[Reactive](spring-boot-webflux)

## jib
jib是谷歌的一个容器工具，在构建容器镜像的时候，不需要写dockerfile，直接使用jib
工具就可以直接构建了
- [官网GitHub](https://github.com/GoogleContainerTools/jib)

## Spring Ai
[Spring AI](spring-ai) 项目是使用Spring AI 框架实现如何快速接入大模型，在如今这大模型时代，用户可以快速的接入大模型开发项目。
包含项目如下：
* [x] [Spring AI 向量化服务](spring-ai/spring-ai-vector)
* [x] [Spring AI 实现MCP](spring-ai/spring-ai-mcp)
