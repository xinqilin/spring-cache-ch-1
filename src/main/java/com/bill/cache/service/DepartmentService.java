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
}
