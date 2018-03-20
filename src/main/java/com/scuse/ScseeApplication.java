package com.scuse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.scuse.mapper")
public class ScseeApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScseeApplication.class, args);
	}
}
