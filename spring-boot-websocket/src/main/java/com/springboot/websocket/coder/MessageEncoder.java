package com.springboot.websocket.coder;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/17 14:50
 * @version：1.0.0
 * @description: 编码器
 */
public class MessageEncoder implements Encoder.Text<Person> {


    @Override
    public String encode(Person person) throws EncodeException {
        String message = JSONObject.toJSONString(person);
        return message;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
