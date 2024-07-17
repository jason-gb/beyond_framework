package com.beyond.university.student.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beyond.university.student.model.mapper.DepartmentMapper;
import com.beyond.university.student.model.vo.Department;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentMapper mapper;
	
	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

}
