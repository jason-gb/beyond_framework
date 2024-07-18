package com.beyond.university.student.model.service;

import java.util.List;

import com.beyond.university.student.model.vo.Department;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentByDeptNo(String deptNo);
}