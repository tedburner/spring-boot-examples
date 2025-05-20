package com.kit.common.util.thread.task;

/**
 * @author: kiturone
 * @create 2017-10-23
 **/
public interface TaskFunction<T> {

    T apply();

    @Override
    boolean equals(Object object);
}
