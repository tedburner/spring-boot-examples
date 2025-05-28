package com.kit.common.util.thread.threadpool;


import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.kit.common.util.thread.task.TaskFunction;
import com.kit.common.util.thread.task.TaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: kiturone
 * @create 2017-10-23
 **/
@Component
public class FastThreadPool implements InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(FastThreadPool.class);
    /**
     * 默认线程处理超时时间 60s
     */
    public static final long DEFAULT_THREAD_PROCESS_TIME_OUT = 60000L;
    /**
     * 默认Future获取时间 20s
     */
    public static final long DEFAULT_FUTURE_GET_TIME_OUT = 20000L;

    /**
     * 线程池维护线程的最少数量
     */
    private static final int CORE_POOL_SIZE = 16;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAXIMUM_POOL_SIZE = 64;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final int KEEP_ALIVE_TIME = 60;

    /**
     * 线程池所使用的缓冲队列的最大数量,用于创建有界的缓冲队列
     */
    private static final int QUEUE_SIZE = 256;

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
     * 提交并发请求
     *
     * @param taskRequest
     * @return
     */
    public <V> List<V> execute(final TaskRequest taskRequest) {
        return execute(taskRequest, DEFAULT_THREAD_PROCESS_TIME_OUT);
    }

    /**
     * 提交并发请求
     *
     * @param taskRequest          请求
     * @param threadProcessTimeout 线程处理时间
     * @return
     */
    public <V> List<V> execute(final TaskRequest taskRequest, long threadProcessTimeout) {
        if (logger.isDebugEnabled()) {
            logger.debug("Try to parallel process Parallel process task count :" + taskRequest.getTaskCount());
        }
        int taskCount = taskRequest.getTaskCount();
        //使用CountDownLatch，等待所有线程执行完后，一起返回所有数据
        final CountDownLatch latch = new CountDownLatch(taskCount);

        List<ListenableFuture<V>> futureList = new ArrayList(taskCount);
        //循环执行方法
        for (int i = 0; i < taskCount; i++) {
            final int index = i;
            //执行具体的方法
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
                    //计数器减一
                    latch.countDown();
                }
            });
            futureList.add(futureTaskResult);
        }
        ListenableFuture<List<V>> finalFuture =
                taskRequest.getIgnoreError() ? Futures.successfulAsList(futureList) : Futures.allAsList(futureList);
        if (taskRequest.getCallback() != null) {
            Futures.addCallback(finalFuture, taskRequest.getCallback(), threadPoolExecutor);
        }
        //等待所有线程执行完后，在继续后面的操作，并设置超时时间
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

    @Override
    public void destroy() throws Exception {
        service.shutdownNow();
        workQueue.clear();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //基于数组结构的有界阻塞队列，按FIFO排序任务
        workQueue = new ArrayBlockingQueue(QUEUE_SIZE);
        //创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名
        threadFactory = new NamedThreadFactory("Parallel-Processor", null, true);
        //用调用者所在的线程来执行任务
        handler = new ThreadPoolExecutor.CallerRunsPolicy();
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, workQueue, threadFactory, handler);
        service = MoreExecutors.listeningDecorator(threadPoolExecutor);
    }
}
