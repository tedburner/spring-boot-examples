# RabbitMq
## 关于延迟队列
在RabbitMQ中，可以设置2种延迟队列，一种是对队列设置过期时间；另外一种
是对消息设置过期时间
### Per-Queue Message TTL
通过在 queue.declare 中设置 x-message-ttl 参数，可以控制被 publish 
到 queue 中的 message 被丢弃前能够存活的时间。当某个 message 在 queue 留存
的时间超过了配置的 TTL 值时，我们说该 message “已死”。
```java
Map<String, Object> args = new HashMap<String, Object>();
args.put("x-message-ttl", 60000);
channel.queueDeclare("myqueue", false, false, false, args);
```

### Per-Message TTL
TTL 设置可以具体到每一条 message 本身，只要在通过 basic.publish 命令
发送 message 时设置 expiration 字段。消息过期，不会马上从队列中抹去，
因为每条消息是否过期时在即将投递到消费者之前判定的。

这种消息过期存在一定的问题，比如连续发送2条消息，第一条设置过期时间1分钟，第二条设置时间30秒，最终
消费的时候，第二条会被第一条阻塞，最终和第一条一起消费。

## 消息确认
在使用消息中间件，我们需要注意消息是否发送成功，以及消息是否消费成功。
### confirm 消息确认机制
消息的确认是指生产者投递消息后，如果 Broker 接收到消息，则会给生产者一个应答。生产者进行接收应答，
用来确认这条消息是否正常的发送到 Broker，这种方式也是消息可靠性投递的核心保障。

- 在channel上启动确认模式：channel.confirmSelect()
- 在channel上添加监听：addConfirmListener，监听成功和失败的返回结果，根据具体的结果对消息进行
重新发送、或记录日志等后续处理

### return 消息机制
生产者通过指定一个 exchange 和 routingkey 把消息送达到某个队列中去，然后消费者监听队列，进行消费
处理。但是在某些情况下，如果我们在发送消息时，当前的 exchange 不存在或者指定的 routingkey 路由不到，
这个时候如果要监听这种不可达的消息，就要使用 return Listener。

### 消息的持久化
为了保证RabbitMQ在重启、奔溃等异常情况下数据没有丢失，除了对消息本身持久化为，还需要将消息传输经过的
队列(queue)，交互机进行持久化(exchange)，持久化以上元素后，消息才算真正RabbitMQ重启不会丢失。
