package com.bill.cache.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Department {

    @Id
    private Integer id;

    @Column
    private String departmentName;


    public Department() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Department(Integer id, String departmentName) {
        super();
        this.id = id;
        this.departmentName = departmentName;
    }


    @Override
    public String toString() {
        return "Department [id=" + id + ", departmentName=" + departmentName + "]";
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getDepartmentName() {
        return departmentName;
    }


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


}
