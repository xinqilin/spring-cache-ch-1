package com.bill.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.bill.cache.bean.Employee;
import com.bill.cache.mapper.EmployeeMapper;

@SpringBootTest
class SpringBootCacheApplicationTests {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate redisTemplate;
	
//	String、List、Set、Hash(散列)、ZSet(有序集合)
	@Test
	public void testRedis() {
//		stringRedisTemplate.opsForValue().append("msg","hello" );
		System.out.println(stringRedisTemplate.opsForValue().get("msg"));;
	}
	
//	@Test
//	void contextLoads() {
//		Employee empById=employeeMapper.getEmpById(1);
//		System.out.println(empById);
//	}

}
