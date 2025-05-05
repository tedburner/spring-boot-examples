package com.example.fluent.mybatis;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import org.junit.jupiter.api.Test;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/22 22:56
 * @description:
 */
public class EntityGeneratorDemo {

    public static final String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong&useSSL=false";


    @Test
    public void generate() throws Exception {
        FileGenerator.build(Empty.class);
    }

    @Tables(
            // 设置数据库连接信息
            url = url, username = "root", password = "123456",
            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置entity类的package值
            basePack = "com.example.fluent.mybatis",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = {"user"})}
    )
    static class Empty {
    }
}
