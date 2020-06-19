package com.golven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.golven.mapper")
public class Springboot08Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08Application.class, args);
	}
}
