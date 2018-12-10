package com.kit.common.serializer;

/**
 * @author: Lucifer
 * @date: 2018-11-30 11:36
 * @description: 序列化
 */
public interface ISerializer<T> {

    /**
     * 序列化方法.
     *
     * @param t
     * @return 序列化后字节数组
     */
    byte[] serialize(T t);

    /**
     * 反序列化方法.
     *
     * @param bytes
     * @return 反序列化后的对象
     */
    T deserialize(byte[] bytes);
}
