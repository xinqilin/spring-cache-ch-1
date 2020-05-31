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
	
//	未定義前
//	@Autowired
//	RedisTemplate redisTemplate;
	
//	自訂義後
	@Autowired
	RedisTemplate<Object,Employee> empResidTemplate;
	
//	String、List、Set、Hash(散列)、ZSet(有序集合)
//	@Test
//	public void testRedis() {
//		stringRedisTemplate.opsForValue().append("msg","hello" );
//		System.out.println(stringRedisTemplate.opsForValue().get("msg"));;
//	}
	
	
//	@Test
//	public void testEmpRedis() {
//		Employee empId=employeeMapper.getEmpById(1);
//		redisTemplate.opsForValue().set("emp-01", empId);
//		
//	}
	
	@Test
	public void testEmpRedis2() {
		Employee empId=employeeMapper.getEmpById(1);
		empResidTemplate.opsForValue().set("emp-01", empId);
	}
	
//	@Test
//	void contextLoads() {
//		Employee empById=employeeMapper.getEmpById(1);
//		System.out.println(empById);
//	}

}
