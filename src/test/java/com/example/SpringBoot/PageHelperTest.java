package com.example.SpringBoot;

import com.example.SpringBoot.model.DO.CityDO;
import com.example.SpringBoot.persist.CityMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @date 2018-01-04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageHelperTest {
    @Autowired
    private CityMapper cityMapper;

    @Test
    public  void selectCityByProvinceIdTest(){
        Page<CityDO> cityDOPage = PageHelper.offsetPage(0,2)
                .doSelectPage(()-> cityMapper.selectCityByProvinceId(1L));
        System.out.println(cityDOPage.getTotal());
    }
}
