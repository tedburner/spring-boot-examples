package com.example.springboot.utils.thread.task;

/**
 * @author lingjun.jlj
 * @create 2017-10-23
 **/
public interface TaskFunction<T>  {
    T apply();

    @Override
    boolean equals(Object object);
}
