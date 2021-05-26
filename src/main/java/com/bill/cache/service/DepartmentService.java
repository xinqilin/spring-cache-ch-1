package com.bill.cache.service;

import com.bill.cache.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import com.bill.cache.bean.Department;
import com.bill.cache.mapper.DepartmentMapper;

@CacheConfig(cacheManager = "deptCacheManager")
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;


    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;


    public Department getDeptById2(Integer id) {
        Department dept = departmentRepository.findById(id).get();
        Cache deptCache = deptCacheManager.getCache("dept");
        deptCache.put("dept:" + id, dept);
        return dept;
    }

//	@Cacheable(cacheNames= {"dept"},cacheManager="deptCacheManager")
//	public Department getDeptById(Integer id) {
//		return deptMapper.getDeptById(id);
//	}


//	@CachePut(cacheNames= "dept" , key="#result.id")
//	public Department updateDept(Department dept) {
//		deptMapper.updateDept(dept);
//		return getDeptById(dept.getId());
//	}


    @CacheEvict(cacheNames = "dept", key = "#id", allEntries = true)
    public void deleteDept(Integer id) {
        System.out.println("要delete的deptId: " + id);
//		假裝有刪  ↓↓↓
//		deptMapper.deleteDept(id);
    }


    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "dept", key = "#name")
            }
    )
    public Department getDeptByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }

}
