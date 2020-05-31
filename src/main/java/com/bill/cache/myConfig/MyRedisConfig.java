package com.bill.cache.myConfig;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.bill.cache.bean.Employee;


@Configuration
public class MyRedisConfig {

	
	@Bean
	public RedisTemplate<Object, Employee> empResidTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException{
		RedisTemplate<Object, Employee> template=new RedisTemplate<Object, Employee>();
		template.setConnectionFactory(redisConnectionFactory);
		//emp序列化後 傳入 defaultSerializer
		Jackson2JsonRedisSerializer<Employee> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<Employee>(Employee.class);
		template.setDefaultSerializer(jackson2JsonRedisSerializer);
		return template;
	}
}
