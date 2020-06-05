package com.bill.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.cache.bean.Department;
import com.bill.cache.mapper.DepartmentMapper;

@Service
public class DepartmentService {

	@Autowired
	DepartmentMapper deptMapper;
	
	public Department getDeptById(Integer id) {
		return deptMapper.getDeptById(id);
	}
	
	public Department updateDept(Department dept) {
		deptMapper.updateDept(dept);
		return getDeptById(dept.getId());
	}
	
	public void deleteDept(Integer id) {
		System.out.println("要delete的deptId: "+id);
//		假裝有刪  ↓↓↓
//		deptMapper.deleteDept(id);
	}
	
	public Department getDeptByName(String name) {
		return deptMapper.getDeptByName(name);
	}
	
}
