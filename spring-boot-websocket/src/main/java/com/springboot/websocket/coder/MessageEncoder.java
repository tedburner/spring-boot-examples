package com.springboot.websocket.coder;

import com.kit.common.util.common.gson.FormatUtils;

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
        String message = FormatUtils.obj2str(person);
        return message;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
