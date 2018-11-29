package com.example.springboot;

import com.example.springboot.domain.DO.CityDO;
import com.example.springboot.persist.CityMapper;
import com.example.springboot.persist.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Lucifer
 * @date 2018-01-04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageHelperTest {

    @Resource
    private CityMapper cityMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectCityByProvinceIdTest() {
        Page<CityDO> cityDOPage = PageHelper.offsetPage(0, 2)
                .doSelectPage(() -> userMapper.selectUser());
        System.out.println("total:" + cityDOPage.getTotal());
    }
}
