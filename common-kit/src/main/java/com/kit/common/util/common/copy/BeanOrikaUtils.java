package com.kit.common.util.common.copy;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author: kiturone
 * @date: 2019/5/21 18:53
 * @description: 基于 orika 的 拷贝方法
 */
public class BeanOrikaUtils {

    public static void copy() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    public static MapperFactory mapperFactory() {

        return new DefaultMapperFactory.Builder().build();
    }
}
