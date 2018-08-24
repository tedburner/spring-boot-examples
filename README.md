# SpringBoot
Spring Boot项目。配置了druid+mybatis+redis。
- 使用了pagehelper的分页插件
- 日志使用logback。
- 使用maven实现开发/测试环境多环境

## 打成jar包
```bash
cd 项目跟目录（和pom.xml同级）
mvn clean package
## 或者执行下面的命令
## 排除测试代码后进行打包
mvn clean package  -Dmaven.test.skip=true
## 如果想根据环境打不同的jar
mvn clean package -Pproduction
```
## 启动jar
```bash
java -jar  target/项目名称-版本号.jar
```
## RabbitMq
项目整合了RabbitMq。代码在rabbitmq分支上面

## kafka
代码在kafka分支上，完成基本功能

## redis
项目整合了Redis，使用spring boot2.0。springboot2.0之后Redis配置和之前的有点区别。

## docker
docker部署代码在docker分支上面。master 分支支持插件直接构建镜像，docker支持dockerfile构建镜像。

## 七牛云
整合了七牛云，使用七牛云的OSS，存储图片文件等功能。

## 测试环境/正式环境
使用profiles实现了环境分开

## MongoDB
整合了MongoDB，使用spring的jpa进行数据库的操作，基本满足正常
项目需求。

## elasticsearch
在分支elasticsearch上

## jib
在分支jib上面
jib是谷歌的一个容器工具，在构建容器镜像的时候，不需要写dockerfile，直接使用jib工具就可以直接构建了
- [官网GitHub](https://github.com/GoogleContainerTools/jib)