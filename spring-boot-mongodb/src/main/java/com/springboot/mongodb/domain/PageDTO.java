package com.springboot.mongodb.domain;

import lombok.Data;

/**
 * @author: Lucifer
 * @date: 2018-12-01 15:01
 * @description:
 */
@Data
public class PageDTO {

    private Integer size;
    private Integer start;
}
