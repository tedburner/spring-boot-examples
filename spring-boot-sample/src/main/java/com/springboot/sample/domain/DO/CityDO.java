package com.springboot.sample.domain.DO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDO implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
