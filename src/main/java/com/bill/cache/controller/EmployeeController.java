package com.bill.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bill.cache.bean.Employee;
import com.bill.cache.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	
	@GetMapping("/emp/{id}")
	public Employee getEmpById(@PathVariable("id") Integer id) {
		return empService.getEmpById(id);
	}
}
