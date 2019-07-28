package com.springboot.websocket.coder;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/17 14:40
 * @version：1.0.0
 * @description: 解码器
 */
public class MessageDecoder implements javax.websocket.Decoder.Text<Person> {


    @Override
    public Person decode(String s) throws DecodeException {
        System.out.println("接收到的参数是：" + s);
        Person person = JSON.parseObject(s, Person.class);
        return person;
    }

    @Override
    public boolean willDecode(String s) {
        return StringUtils.isNotBlank(s);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
