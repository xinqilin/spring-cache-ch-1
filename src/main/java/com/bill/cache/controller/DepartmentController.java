package com.bill.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bill.cache.bean.Department;
import com.bill.cache.service.DepartmentService;

@RestController
public class DepartmentController {

	
	@Autowired
	DepartmentService deptService;
	
	
//	@GetMapping("/dept/{id}")
//	public Department getDeptById(@PathVariable("id") Integer id) {
//		return deptService.getDeptById(id);
//	}
	
	@GetMapping("/dept/{id}")
	public Department getDeptById(@PathVariable("id") Integer id) {
		return deptService.getDeptById2(id);
	}
	
//	@GetMapping("/dept")
//	public Department updateDept(Department dept) {
//		deptService.updateDept(dept);
//		return getDeptById(dept.getId());
//	}
	
	@GetMapping("/delDept/{id}")
	public String deleteEmp(@PathVariable("id") Integer id) {
		deptService.deleteDept(id);
		return "Delete Success!!!";
	}
	
	@GetMapping("/deptName/{name}")
	public Department getDeptByName(@PathVariable("name") String name) {
		return deptService.getDeptByName(name);
	}
}
