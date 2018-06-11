package com.example.springboot.persist.kafkaMessage;

import com.example.springboot.model.DO.CityDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

public interface KafkaMessageMapper {

    Long insert(CityDO cityDO);
}
