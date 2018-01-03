package com.example.SpringBoot.service;

import com.example.SpringBoot.dto.City;
import com.example.SpringBoot.dto.ProvinceDO;
import com.example.SpringBoot.persist.CityMapper;
import com.example.SpringBoot.persist.ProvinceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

@Service
public class CityServiceImpl implements CityService{

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
    public City getCityByName(String name) {
        return cityMapper.findByName(name);
    }

    @Override   //spring注解类缓存，value必填，key为Redis的key
    @Cacheable(value = "redis",key = "'cityId_'+#id")
    public City getCityById(Long id) {
        return cityMapper.findById(id);
    }

    @Override
    public void addAddress() {
        //对于多个插入，使用事务回滚，防止插入报错，插入错误数据
        transactionTemplate.execute(status->{
            try {
                ProvinceDO province = new ProvinceDO();
                province.setName("浙江省");
                provinceMapper.addProvince(province);

                City city = new City();
                city.setProvinceId(province.getId());
                city.setName("金华市");
                city.setDescription("金华火腿");
                cityMapper.addCity(city);

            }catch (Exception e){
                status.setRollbackOnly();
                log.info("添加地址出现错误"+e);
                throw e;
            }
            return null;
        });
    }

}