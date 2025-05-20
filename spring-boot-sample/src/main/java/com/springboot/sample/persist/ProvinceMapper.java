package com.springboot.sample.persist;

import com.springboot.sample.domain.DO.ProvinceDO;

/**
 * @author kiturone
 * @date 2017/12/2
 */
public interface ProvinceMapper {

    Long addProvince(ProvinceDO province);

    ProvinceDO selectProvinceById(Long id);
}
