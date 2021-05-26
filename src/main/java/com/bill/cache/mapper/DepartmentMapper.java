package com.bill.cache.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bill.cache.bean.Department;

@Deprecated
@Mapper
public interface DepartmentMapper {

	
	@Select("SELECT * FROM Department WHERE ID=#{id}")
	public Department getDeptById(Integer id);
	
	@Insert("INSERT INTO Department VALUES(null,#{departmentName})")
	public void addDept(Department dept);
	
	@Update("UPDATE Department SET departmentName=#{departmentName} WHERE id=#{id}")
	public void updateDept(Department dept);
	
	@Delete("DELETE FROM Department WHERE id=#{id}")
	public void deleteDept(Integer id);
	
	@Select("SELECT * FROM Department WHERE departmentName=#{departmentName}")
	public Department getDeptByName(String departmentName);
}
