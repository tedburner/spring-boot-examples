package com.example.fluent.mybatis.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * UserEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
        chain = true
)
@EqualsAndHashCode(
        callSuper = false
)
@AllArgsConstructor
@NoArgsConstructor
@FluentMybatis(
        table = "user",
        schema = "test"
)
public class UserEntity extends RichEntity {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("age")
    private Integer age;

    @TableField("card")
    private String card;

    @TableField("create_time")
    private Date createTime;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("status")
    private Integer status;

    @TableField("update_time")
    private Date updateTime;

    @Override
    public final Class entityClass() {
        return UserEntity.class;
    }
}
