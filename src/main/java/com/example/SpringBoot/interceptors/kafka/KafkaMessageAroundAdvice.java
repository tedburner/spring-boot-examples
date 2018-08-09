package com.example.springboot.interceptors.kafka;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.example.springboot.base.annotation.KafkaMessageAnnotation;
import com.example.springboot.common.enums.kafka.KafkaMessageStatusEnum;
import com.example.springboot.model.DO.kafkaMessage.KafkaMessageDO;
import com.example.springboot.persist.kafkaMessage.KafkaMessageMapper;
import com.example.springboot.utils.common.TranslateUtil;
import com.example.springboot.utils.common.gson.FormatUtils;
import com.example.springboot.utils.delayTask.TaskMessage;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.DelayQueue;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 16:05
 * @Description: annotation方法拦截器
 */
public class KafkaMessageAroundAdvice implements Advice,
        MethodInterceptor, ApplicationContextAware {

    private static Logger log = LoggerFactory.getLogger(KafkaMessageAroundAdvice.class);

    private static ApplicationContext applicationContext;

    @Autowired
    private KafkaMessageMapper kafkaMessageMapper;

    private static Boolean signal = true;

    /**
     * 延迟调用推送队列
     */
    private static DelayQueue<TaskMessage> taskMessages = new DelayQueue<>();

    static {
        new Thread(() -> {
            while (signal) {
                try {
                    TaskMessage taskMessage = taskMessages.take();
                    taskMessage.getFunction().apply("kafka-apply");
                } catch (Exception e) {
                    log.error("kafka delayQueue fail", e);
                }
            }
        }).start();
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        //判断是否含有方法上是否KafkaMessageAnnotation注释
        if (methodInvocation.getMethod().isAnnotationPresent(KafkaMessageAnnotation.class)) {
            Object[] args = methodInvocation.getArguments();
            Object target = methodInvocation.getThis();

            Class targetClass = AopUtils.getTargetClass(target);
            Field fieldTopic = targetClass.getField("topic");
            String topic = TranslateUtil.getStrOfObj(fieldTopic.get(target));

            KafkaMessageDO messageDO = new KafkaMessageDO();

            messageDO.setTopic(topic);

            Object messageBody = args[0];

            messageDO.setMessageBody(FormatUtils.obj2str(messageBody));
            messageDO.setStatus(KafkaMessageStatusEnum.NOT_COMPLETE.getCode());

            Schema schema = RuntimeSchema.getSchema(messageBody.getClass());
            LinkedBuffer buffer = LinkedBuffer.allocate(4096);
            byte[] bytes = ProtostuffIOUtil.toByteArray(messageBody, schema, buffer);
            messageDO.setMessageByte(bytes);

            if (log.isDebugEnabled()) {
                log.info("KafkaMessageAroundAdvice message=" + messageDO.toString());
            }

            kafkaMessageMapper = applicationContext.getBean(KafkaMessageMapper.class);

            int res = kafkaMessageMapper.insertKafkaMessage(messageDO);
            if (res <= 0) {
                log.error("KafkaMessageAroundAdvice insertKafkaMessage error, res<=0");
                throw new RuntimeException("insertKafkaMessage error, res<=0");
            }

            Class messageClass = messageBody.getClass();
            String methodName = "setKafkaMessageId";

            Class[] argsClass = new Class[1];
            Object[] methodArgs = new Object[1];
            methodArgs[0] = messageDO.getId();
            argsClass[0] = methodArgs[0].getClass();

            Method messageMethod = messageClass.getMethod(methodName, argsClass);
            messageMethod.invoke(messageBody, methodArgs);
            //延迟调用先返回
            TaskMessage taskMessage = new TaskMessage(1000L, "kafka-delay", (signal) -> {
                try {
                    return methodInvocation.proceed();
                } catch (Throwable throwable) {
                    log.error("delayQueue Error", throwable);
                }
                return 0;
            });
            boolean succ = taskMessages.offer(taskMessage);
            if (succ) {
                return new Object();
            } else {
                log.warn("delayQueue filled");
                return methodInvocation.proceed();
            }
        }
        return methodInvocation.proceed();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        KafkaMessageAroundAdvice.applicationContext = applicationContext;
    }

    @Override
    protected void finalize() throws Throwable {
        signal = false;
        super.finalize();
    }
}
