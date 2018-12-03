package com.kit.common.util.thread.threadpool;


import com.kit.common.util.thread.task.TaskFunction;
import com.kit.common.util.thread.task.TaskRequest;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Lucifer
 * @create 2017-10-23
 **/
@Service("fastThreadPool")
public class FastThreadPool {

    private static Logger logger = LoggerFactory.getLogger(FastThreadPool.class);


    public static final int DEFAULT_MIN_THREAD_COUNT = 16;

    public static final int DEFAULT_MAX_THREAD_COUNT = 64;

    public static final int DEFAULT_KEEP_ALIVE_TIME = 60;

    public static final int DEFAULT_QUEUE_SIZE = 256;

    public static final long DEFAULT_THREAD_PROCESS_TIME_OUT = 60000L;//60s

    public static final long DEFAULT_FUTURE_GET_TIME_OUT = 20000L; //20s

    /**
     * 线程池维护线程的最少数量
     */
    private int corePoolSize = DEFAULT_MIN_THREAD_COUNT;

    /**
     * 线程池维护线程的最大数量
     */
    private int maximumPoolSize = DEFAULT_MAX_THREAD_COUNT;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveTime = DEFAULT_KEEP_ALIVE_TIME;

    /**
     * 线程池所使用的缓冲队列的最大数量,用于创建有界的缓冲队列
     */
    private int queueSize = DEFAULT_QUEUE_SIZE;

    /**
     * 线程池所使用的缓冲队列
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * 线程池对拒绝任务的处理策略
     */
    private RejectedExecutionHandler handler;

    private ThreadFactory threadFactory;

    private ThreadPoolExecutor threadPoolExecutor;

    private ListeningExecutorService service;

    /**
     * @PostConstruct 在spring容器初始化时执行该方法
     */
    @PostConstruct
    public void initialize() {
        workQueue = new ArrayBlockingQueue(queueSize); //基于数组结构的有界阻塞队列，按FIFO排序任务
        threadFactory = new NamedThreadFactory("Parallel-Processor", null, true);//创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名
        handler = new ThreadPoolExecutor.CallerRunsPolicy(); //用调用者所在的线程来执行任务
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory, handler);
        service = MoreExecutors.listeningDecorator(threadPoolExecutor);
    }

    @PreDestroy
    public void stop() {
        service.shutdownNow();
        workQueue.clear();
    }

    /**
     * 提交并发请求
     *
     * @param taskRequest
     */
    public <V> List<V> execute(final TaskRequest taskRequest) {
        long threadProcessTimeout = DEFAULT_THREAD_PROCESS_TIME_OUT;
        return execute(taskRequest, threadProcessTimeout);
    }

    /**
     * 提交并发请求
     *
     * @param taskRequest
     * @param
     */
    public <V> List<V> execute(final TaskRequest taskRequest, long threadProcessTimeout) {
        if (logger.isDebugEnabled()) {
            logger.debug("Try to parallel process Parallel process task count :" + taskRequest.getTaskCount());
        }
        final CountDownLatch latch = new CountDownLatch(taskRequest.getTaskCount());

        List<ListenableFuture<V>> futureList = new ArrayList(taskRequest.getTaskCount());
        for (int i = 0; i < taskRequest.getTaskCount(); i++) {
            final int index = i;
            ListenableFuture<V> futureTaskResult = service.submit(() -> {
                V result = null;
                try {
                    long startTime = System.currentTimeMillis();
                    TaskFunction<V> taskFunction = taskRequest.getTaskList().get(index);
                    result = taskFunction.apply();
                    long endTime = System.currentTimeMillis();
                    if (logger.isDebugEnabled()) {
                        logger.debug("Try to parallel process Parallel process task totalTime :" + (endTime - startTime));
                    }
                    return result;
                } catch (Throwable e) {
                    logger.error("thread pool process future task failed:", e);
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();//计数器减一
                }
            });
            futureList.add(futureTaskResult);
        }
        ListenableFuture<List<V>> finalFuture =
                taskRequest.getIgnoreError() ? Futures.successfulAsList(futureList) : Futures.allAsList(futureList);
        if (taskRequest.getCallback() != null) {
            Futures.addCallback(finalFuture, taskRequest.getCallback());
        }
        try {
            latch.await(threadProcessTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("latch.await parallel process failed, maybe timeout:", e);
        }
        List<V> taskResultList;
        try {
            taskResultList = finalFuture.get(DEFAULT_FUTURE_GET_TIME_OUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("thread pool get result exception:", e);
            if (finalFuture.cancel(true)) {
                logger.warn("task execution time out, thread pool cancel task success!");
            } else {
                logger.error("thread pool cancel task failed!");
            }
            throw new RuntimeException("thread pool execute error", e);
        }
        return taskResultList;
    }
}
