package com.example.SpringBoot;

import com.example.SpringBoot.dto.DTO.message.SampleMessageDTO;
import com.example.SpringBoot.kafka.Producer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.soap.MTOM;


/**
 * * Spring Boot 应用启动类
 * */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.example.SpringBoot.persist")
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ApplicationRunner runner(Producer producer) {
		return (args) -> producer.send(new SampleMessageDTO(1, "A simple test message"));
	}

}
