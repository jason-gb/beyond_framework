package com.beyond.university.student.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beyond.university.student.model.service.DepartmentService;
import com.beyond.university.student.model.vo.Department;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {
	private final DepartmentService departmentService;
	
	@GetMapping("/student/search")
	public ModelAndView search(ModelAndView modelAndView) {
		List<Department> departments = departmentService.getDepartments();
		
		System.out.println(departments);
		System.out.println(departments.size());
		
		modelAndView.addObject("departments", departments);
		modelAndView.setViewName("student/search");
		
		return modelAndView;
	}
	
}
