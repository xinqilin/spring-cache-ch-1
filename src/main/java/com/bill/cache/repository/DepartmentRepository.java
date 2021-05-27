package com.bill.cache.repository;


import com.bill.cache.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public Department findByDepartmentName(String departmentName);

}