## Spring Boot Starter 创建过程
- 首先项目中引入依赖：spring-boot-autoconfigure和spring-boot-configuration-processor
- 创建 `AutoConguration` 类，这里需要解释两个注释。@ConditionalOnClass(MonitorService.class)
放在自动装载类上，标识只有当MonitorService类存在的时候才执行自动装配。@ConditionalOnMissingBean 放在创建Bean上，标识只有当前实体类不存在的时候才创建。
- 创建配置文件，可以从 application.yml中读取配置信息
- 在 `resource` 目录下，创建 `META-INF` 文件，并在该文件夹下创建`spring.properties`，添加需要自动装配的类。Spring Boot会在启动时，合并spring.properties中的内容。
- 最后打包使用