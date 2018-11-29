package com.example.springboot.persist;

import com.example.springboot.domain.DO.ProvinceDO;

/**
 * @author Lucifer
 * @date 2017/12/2
 */
public interface ProvinceMapper {

    Long addProvince(ProvinceDO province);

    ProvinceDO selectProvinceById(Long id);
}
