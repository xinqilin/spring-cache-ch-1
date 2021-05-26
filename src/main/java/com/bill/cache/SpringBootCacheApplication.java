package com.bill.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@MapperScan("com.bill.cache.mapper")
//@EntityScan(value = {"com.bill.cache.bean"})
//@EnableJpaRepositories(basePackages={"com.bill.cache.repository"})
@SpringBootApplication
@EnableCaching
public class SpringBootCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}

}
