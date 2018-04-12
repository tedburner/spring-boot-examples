package com.example.SpringBoot.service.impl;

import com.example.SpringBoot.dto.DO.CityDO;
import com.example.SpringBoot.dto.DO.ProvinceDO;
import com.example.SpringBoot.persist.CityMapper;
import com.example.SpringBoot.persist.ProvinceMapper;
import com.example.SpringBoot.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

@Service
public class CityServiceImpl implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ProvinceMapper provinceMapper;

    @Resource  //也可以使用@Transactional
    private TransactionTemplate transactionTemplate;

    @Override
    public CityDO getCityByName(String name) {
        return cityMapper.selectByName(name);
    }

    @Override   //spring注解类缓存，value必填，key为Redis的key
    @Cacheable(value = "redis",key = "'cityId_'+#id")
    public CityDO getCityById(Long id) {
        return cityMapper.selectById(id);
    }

    @Override
    public void addAddress() {
        //对于多个插入，使用事务回滚，防止插入报错，插入错误数据
        transactionTemplate.execute(status->{
            try {
                ProvinceDO province = new ProvinceDO();
                province.setName("浙江省");
                provinceMapper.addProvince(province);

                CityDO builder = CityDO.CityBuilder.aCity()
                        .withProvinceId(province.getId())
                        .withName("金华市")
                        .withDescription("金华火腿")
                        .build();
                cityMapper.addCity(builder);

            }catch (Exception e){
                status.setRollbackOnly();
                log.info("添加地址出现错误"+e);
                throw e;
            }
            return null;
        });
    }

}
