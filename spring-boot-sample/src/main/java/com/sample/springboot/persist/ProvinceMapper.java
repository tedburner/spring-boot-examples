package com.sample.springboot.persist;

import com.sample.springboot.domain.DO.ProvinceDO;

/**
 * @author Lucifer
 * @date 2017/12/2
 */
public interface ProvinceMapper {

    Long addProvince(ProvinceDO province);

    ProvinceDO selectProvinceById(Long id);
}
