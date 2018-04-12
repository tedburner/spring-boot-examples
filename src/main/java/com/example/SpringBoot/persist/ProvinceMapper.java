package com.example.SpringBoot.persist;

import com.example.SpringBoot.dto.DO.ProvinceDO;

/**
 * @author lingjun.jlj
 * @date 2017/12/2
 */
public interface ProvinceMapper {

    Long addProvince(ProvinceDO province);

    ProvinceDO selectProvinceById(Long id);
}
