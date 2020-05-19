package com.kit.common.util.thread.callback;

import com.google.common.util.concurrent.FutureCallback;

/**
 * @author Lucifer
 * @create 2017-10-23
 **/
public interface SuccessCallBack<V> extends FutureCallback<V> {

    /**
     * 成功时，Future 回调结果
     *
     * @param throwable
     */
    default void onSuccess(Throwable throwable) {
    }
}