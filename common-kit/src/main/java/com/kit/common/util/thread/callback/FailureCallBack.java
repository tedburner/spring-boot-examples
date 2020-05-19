package com.kit.common.util.thread.callback;

import com.google.common.util.concurrent.FutureCallback;

/**
 * @author lingjun.jlj
 * @create 2017-10-23
 **/
public interface FailureCallBack<V> extends FutureCallback<V> {

    /**
     * 当计算失败或者取消时，回调
     *
     * @param result
     */
    default void onFailure(V result) {
    }
}
