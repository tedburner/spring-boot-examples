# SpringBoot
Spring Boot项目。配置了druid+mybatis+redis。同时项目中使用了pagehelper的分页插件
## 打成jar包
```bash
cd 项目跟目录（和pom.xml同级）
mvn clean package
## 或者执行下面的命令
## 排除测试代码后进行打包
mvn clean package  -Dmaven.test.skip=true
```
## 启动jar
```bash
java -jar  target/项目名称-版本号.jar
```