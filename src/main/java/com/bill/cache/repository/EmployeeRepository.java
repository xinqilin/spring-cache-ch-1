package com.bill.cache.repository;

import com.bill.cache.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByLastName(String lastName);

    @Query(value = "SELECT e.* FROM department d " +
            "INNER JOIN employee e on d.id = e.d_id " +
            "WHERE d.id = ?1 ", nativeQuery = true)
    public List<Employee> findEmployeeByDepartment(Integer deptId);
}