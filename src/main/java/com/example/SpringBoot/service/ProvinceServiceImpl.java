package com.example.SpringBoot.service;

import com.example.SpringBoot.dto.ProvinceDO;
import com.example.SpringBoot.persist.ProvinceMapper;
import com.example.SpringBoot.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lingjun.jlj
 * @create 2017-12-04
 **/
@Service
public class ProvinceServiceImpl implements ProvinceService{

    private static final Logger log = LoggerFactory.getLogger(ProvinceServiceImpl.class);

    @Resource
    private ProvinceMapper provinceMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ProvinceDO getProvince(Long id) {
        String key = "provinceId_"+id;
        if (redisUtils.exists(key)){
            //todo redis对Java8的LocalDateTime的序列化有问题，需要看看
            ProvinceDO province = (ProvinceDO)redisUtils.get(key);
            log.info("从缓存中获取省份>>"+province.toString());
            return province;
        }
        ProvinceDO province = provinceMapper.getProvince(id);

        //插入缓存
        redisUtils.set(key,province);
        log.info("存入缓存中>>"+province.toString());
        return province;
    }
}
