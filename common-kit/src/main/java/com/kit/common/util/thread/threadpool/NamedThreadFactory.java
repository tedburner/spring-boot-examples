package com.kit.common.util.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lucifer
 * @create 2017-10-23
 **/
public class NamedThreadFactory implements ThreadFactory {

    /**thread name前缀*/
    private String prefix;
    /**ThreadGroup*/
    private ThreadGroup group;
    /**是否是守护进程*/
    private boolean isDaemon;
    /**thread 编号*/
    private AtomicInteger tNo;

    public NamedThreadFactory(String prefix) {
        this(prefix, null, false);
    }

    public NamedThreadFactory(String prefix, ThreadGroup group, boolean isDaemon) {
        tNo = new AtomicInteger(0);
        this.prefix = prefix;
        if (null != group) {
            this.group = group;
        } else {
            SecurityManager sm = System.getSecurityManager();
            group = (sm != null) ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
        }
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, prefix + "-Thread-" + tNo.getAndIncrement());
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
