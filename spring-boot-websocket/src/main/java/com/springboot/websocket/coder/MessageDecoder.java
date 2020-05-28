package com.springboot.websocket.coder;

import com.google.gson.reflect.TypeToken;
import com.kit.common.test.UserDTO;
import com.kit.common.util.common.gson.FormatUtils;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;
import java.lang.reflect.Type;

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
        Type type = new TypeToken<Person>() {
        }.getType();
        Person person = FormatUtils.str2obj(s, type);
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
