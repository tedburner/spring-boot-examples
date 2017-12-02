package com.example.SpringBoot;

import com.example.SpringBoot.dto.City;
import com.example.SpringBoot.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Resource
	private CityService cityService;


	@Test
	public void contextLoads() {
		City city = cityService.getCityByName("杭州");
		System.out.println(city);

	}

	@Test
	public void redisTest(){

		City city = cityService.getCityById(1L);
		System.out.println(city);
	}

	@Test
	public void addAddressTest(){
		cityService.addAddress();
	}

}
