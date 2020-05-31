package com.bill.cache.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bill.cache.bean.Employee;

@Mapper
public interface EmployeeMapper {

	@Select("SELECT * FROM EMPLOYEE WHERE ID=#{id}")
	public Employee getEmpById(Integer id);
	
	@Insert("INSERT INTO EMPLOYEE VALUES(null,#{lastName},#{email},#{gender},#{dId})")
	public void addEmp(Employee emp);
	
	@Update("UPDATE EMPLOYEE SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
	public void updateEmp(Employee emp);
	
	@Delete("DELETE FROM EMPLOYEE WHERE id=#{id}")
	public void deleteEmp(Integer id);
	
	@Select("SELECT * FROM EMPLOYEE WHERE lastName=#{name}")
	public Employee getEmpByName(String name);
}
