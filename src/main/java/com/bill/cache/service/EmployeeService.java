package com.bill.cache.service;

import com.bill.cache.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.bill.cache.bean.Employee;
import com.bill.cache.mapper.EmployeeMapper;


//@CacheConfig(cacheNames="emp")  方便的prefix
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Cacheable(cacheNames = {"emp"}/*,keyGenerator="myKeyGenerator" ,condition="#a0>1" */)
    public Employee getEmpById(Integer id) {
        System.out.println("select emp's id: " + id);
        return employeeRepository.findById(id).get();
    }


    @CachePut(cacheNames = "emp", key = "#result.id")  //key="#emp.id"
    public Employee updateEmp(Employee emp) {
        System.out.println("要update的emp: " + emp);
        employeeRepository.save(emp);
        return emp;
    }

    //allEntries=true 、 beforeInvocation=true
    @CacheEvict(cacheNames = "emp", key = "#id", allEntries = true)
    public void deleteEmp(Integer id) {
        System.out.println("要delete的empId: " + id);
//		假裝有刪  ↓↓↓
//		empMapper.deleteEmp(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "emp", key = "#name")
            }
//				,
//				put= {
//						@CachePut(cacheNames="emp",key="#result.id"),
//						@CachePut(cacheNames="emp",key="#result.email")
//				}
    )
    public Employee getEmpByName(String name) {
        System.out.println("使用名字進行查詢");
        System.out.println("select emp's name: " + name);
        return employeeRepository.findByLastName(name);
    }
}
