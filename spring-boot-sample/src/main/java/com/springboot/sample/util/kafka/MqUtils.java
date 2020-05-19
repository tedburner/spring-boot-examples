package com.springboot.sample.util.kafka;

/**
 * @author: lingjun.jlj
 * @date: 2018/11/13 10:14
 * @description:
 */
public class MqUtils {


    public static String getKey(String groupId, String topic, String key) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(groupId).append("#").append(topic).append("#").append(key);
        return buffer.toString();
    }
}
