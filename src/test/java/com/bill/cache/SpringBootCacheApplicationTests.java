package com.bill.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bill.cache.bean.Employee;
import com.bill.cache.mapper.EmployeeMapper;

@SpringBootTest
class SpringBootCacheApplicationTests {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Test
	void contextLoads() {
		Employee empById=employeeMapper.getEmpById(1);
		System.out.println(empById);
	}

}
