package com.bill.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.cache.bean.Employee;
import com.bill.cache.mapper.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper empMapper;
	
	public Employee getEmpById(Integer id) {
		System.out.println("id: "+id);
		return empMapper.getEmpById(id);
	}
}