package com.bill.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bill.cache.bean.Employee;
import com.bill.cache.mapper.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper empMapper;
	
	@Cacheable(cacheNames= {"emp"}/*,keyGenerator="myKeyGenerator" ,condition="#a0>1" */)
	public Employee getEmpById(Integer id) {
		System.out.println("select emp's id: "+id);
		return empMapper.getEmpById(id);
	}
	
	
	@CachePut(cacheNames= "emp" , key="#result.id")  //key="#emp.id"
	public Employee updateEmp(Employee emp){
		System.out.println("要update的emp: "+emp);
		empMapper.updateEmp(emp);
		return emp;
	}
	
	
	@CacheEvict(cacheNames= "emp" , key="#id")
	public void deleteEmp(Integer id) {
		System.out.println("要delete的empId: "+id);
//		假裝有刪  ↓↓↓
//		empMapper.deleteEmp(id);
	}
}
