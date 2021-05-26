package com.bill.cache.repository;

import com.bill.cache.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByLastName(String lastName);
}