package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mayikt.mapper")
public class Springboot05MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot05MybatisApplication.class, args);
	}

}
