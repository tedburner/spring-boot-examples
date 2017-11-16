package com.example.SpringBoot.utils.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingjun.jlj
 * @create 2017-10-23
 **/
public class NamedThreadFactory implements ThreadFactory {

    private String prefix;
    private ThreadGroup group;
    private boolean  isDaemon;
    private AtomicInteger tNo;

    public NamedThreadFactory(String prefix) {
        this(prefix, null, false);
    }

    public NamedThreadFactory(String prefix, ThreadGroup group, boolean isDaemon) {
        tNo = new AtomicInteger(0);
        this.prefix = prefix;
        if(null != group) {
            this.group = group;
        } else {
            SecurityManager sm = System.getSecurityManager();
            group = (sm != null) ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
        }
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, new StringBuilder(prefix).append("-Thread-").append(tNo.getAndIncrement()).toString());
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
