package com.bill.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bill.cache.mapper")
@SpringBootApplication
public class SpringBootCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}

}
