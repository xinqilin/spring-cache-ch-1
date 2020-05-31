package com.bill.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bill.cache.bean.Employee;
import com.bill.cache.mapper.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper empMapper;
	
	@Cacheable(cacheNames= {"emps"},keyGenerator="myKeyGenerator" ,condition="#a0>1")
	public Employee getEmpById(Integer id) {
		System.out.println("select emp's id: "+id);
		return empMapper.getEmpById(id);
	}
}
