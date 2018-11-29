package com.sample.springboot.service.common.impl;

import com.sample.springboot.domain.DO.CityDO;
import com.sample.springboot.domain.DO.ProvinceDO;
import com.sample.springboot.persist.CityMapper;
import com.sample.springboot.persist.ProvinceMapper;
import com.sample.springboot.service.common.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/

@Service
public class CityServiceImpl implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    @Resource
    private CityMapper cityMapper;
    @Resource
    private ProvinceMapper provinceMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public CityDO getCityByName(String name) {
        return cityMapper.selectByName(name);
    }

    /**
     * @Cacheable 如果第一次就会把缓存存储到Redis，之后如果有相对于key的缓存就会直接冲Redis中获取
     * 参数： value缓存名、 key缓存键值、 condition满足缓存条件、unless否决缓存条件
     * @CachePut 但会把方法的返回值放入缓存中, 主要用于数据新增和修改方法。
     * @CacheEvict 方法执行成功后会从缓存中移除相应数据。
     * 参数： value缓存名、 key缓存键值、 condition满足缓存条件、 unless否决缓存条件、 allEntries是否移除所有数据（设置为true时会移除所有缓存）
     */
    @Override
    @Cacheable(value = "city", key = "'cityId_'+#id")
    public CityDO getCityById(Long id) {
        return cityMapper.selectById(id);
    }

    @Override
    public void addAddress() {
        //对于多个插入，使用事务回滚，防止插入报错，插入错误数据
        transactionTemplate.execute(status -> {
            try {
                ProvinceDO province = new ProvinceDO();
                province.setName("福建省");
                provinceMapper.addProvince(province);

                CityDO builder = CityDO.CityDOBuilder.aCityDO()
                        .withProvinceId(province.getId())
                        .withName("厦门市")
                        .withDescription("？？？")
                        .build();
                cityMapper.addCity(builder);

            } catch (Exception e) {
                status.setRollbackOnly();
                log.info("添加地址出现错误" + e);
                throw e;
            }
            return null;
        });

    }

    @Override
    public List<CityDO> findCityByProvinceId(Long provinceId) {
        return cityMapper.selectCityByProvinceId(provinceId);
    }

}
