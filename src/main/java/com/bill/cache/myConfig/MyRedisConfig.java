package com.bill.cache.myConfig;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

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
	@Bean
	public RedisCacheConfiguration empRedisCacheConfiguration() {
		RedisSerializer<Employee> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
		SerializationPair<Employee> serializationPair = RedisSerializationContext.SerializationPair
																				 .fromSerializer(jackson2JsonRedisSerializer);

		return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationPair);
	}
	@Bean
	public RedisCacheManager empCacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheManagerBuilder builder = RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
				.cacheDefaults(empRedisCacheConfiguration());
		RedisCacheManager redisCacheManager = builder.build();
		return redisCacheManager;
	}


	
//	@Bean
//	public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> empResidTemplate) {
//		RedisCacheManager cacheManager=new RedisCacheManager(empResidTemplate);
//		cacheManager.setUsePrefix(true);
//		return cacheManager;
//	}
}
