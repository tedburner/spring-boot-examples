package com.example.SpringBoot.utils.thread.callBack;

import com.google.common.util.concurrent.FutureCallback;

/**
 * @author lingjun.jlj
 * @create 2017-10-23
 **/
public interface SuccessCallBack<V> extends FutureCallback<V> {

    default void onFailure(Throwable throwable) {}
}