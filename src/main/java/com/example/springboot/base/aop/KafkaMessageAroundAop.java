package com.example.springboot.base.aop;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.example.springboot.common.enums.kafka.KafkaMessageStatusEnum;
import com.example.springboot.model.DO.kafkaMessage.KafkaMessageDO;
import com.example.springboot.persist.kafkaMessage.KafkaMessageMapper;
import com.example.springboot.utils.common.TranslateUtil;
import com.example.springboot.utils.common.gson.FormatUtils;
import com.example.springboot.utils.thread.task.TaskFunction;
import com.example.springboot.utils.thread.task.TaskRequest;
import com.example.springboot.utils.thread.threadpool.FastThreadPool;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lingjun.jlj
 * @create 2017-11-30
 * @Description: kafka message切面
 **/
@Aspect
@Component
@Slf4j
public class KafkaMessageAroundAop {


    @Resource
    private FastThreadPool fastThreadPool;


    @Resource
    private KafkaMessageMapper kafkaMessageMapper;

    /**
     * 在service执行前打印log
     */
//    @Before("execution(* com.example.springboot.service..*.*(..))")
//    public void before(JoinPoint point) {
//        log.info("@Before 准备执行: " + point.getSignature().getDeclaringTypeName()
//                + " 的 " + point.getSignature().getName() + "方法");
//    }

    @Pointcut("@annotation(com.example.springboot.base.annotation.KafkaMessageAnnotation)")
    public void messageSend() {
        System.out.println("Pointcut");
    }

    @Around("messageSend()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();

        //获取class中topic的值
        Class targetClass = AopUtils.getTargetClass(joinPoint.getTarget());
        Field fieldTopic = targetClass.getField("topic");
        String topic = TranslateUtil.getStrOfObj(fieldTopic.get(target));

        KafkaMessageDO messageDO = new KafkaMessageDO();

        messageDO.setTopic(topic);

        Object messageBody = args[1];

        messageDO.setMessageBody(FormatUtils.obj2str(messageBody));
        messageDO.setStatus(KafkaMessageStatusEnum.NOT_COMPLETE.getCode());

        Schema schema = RuntimeSchema.getSchema(messageBody.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] bytes = ProtostuffIOUtil.toByteArray(messageBody, schema, buffer);
        messageDO.setMessageByte(bytes);

        if (log.isDebugEnabled()) {
            log.info("KafkaMessageAroundAdvice message=" + messageDO.toString());
        }

        List<TaskFunction> functionList = Lists.newArrayList();
        functionList.add(() -> {
            int res = kafkaMessageMapper.insertKafkaMessage(messageDO);
            if (res <= 0) {
                log.error("KafkaMessageAroundAdvice insertKafkaMessage error, res<=0");
                throw new RuntimeException("insertKafkaMessage error, res<=0");
            }
            return res;
        });

        fastThreadPool.execute(new TaskRequest(functionList, false));

        //kafkaMessage模板插入？？？
//        Class messageClass = messageBody.getClass();
//        String methodName = "setKafkaMessageId";
//
//        Class[] argsClass = new Class[1];
//        Object[] methodArgs = new Object[1];
//        methodArgs[0] = messageDO.getId();
//        argsClass[0] = methodArgs[0].getClass();
//
//        Method messageMethod = messageClass.getMethod(methodName, argsClass);
//        messageMethod.invoke(messageBody, methodArgs);
        return joinPoint.proceed();
    }

}
