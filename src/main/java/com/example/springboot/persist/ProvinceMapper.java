package com.example.springboot.persist;

import com.example.springboot.model.DO.ProvinceDO;

/**
 * @author lingjun.jlj
 * @date 2017/12/2
 */
public interface ProvinceMapper {

    Long addProvince(ProvinceDO province);

    ProvinceDO selectProvinceById(Long id);
}
