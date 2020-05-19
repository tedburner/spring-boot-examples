package com.springboot.sample;

import com.kit.common.serializer.HessianSerializer;
import com.kit.common.serializer.ISerializer;
import org.junit.Test;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-03 10:42
 * @description:
 */
public class HessianSerializerTests extends SpringBootSampleTests {

    public static void main(String[] args) {
         ISerializer serializer = new HessianSerializer();
        //序列化
        byte[] bytes = serializer.serialize("今天是星期一，阴雨天，又要开始上班了，一点都不想上班");
        System.out.println(bytes);

        //反序列化
        String str = (String) serializer.deserialize(bytes);
        System.out.println(str);
    }

    @Test
    public void HessianSerializerTest() {

    }
}
