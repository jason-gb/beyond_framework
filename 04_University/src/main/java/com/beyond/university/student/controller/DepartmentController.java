package com.beyond.university.student.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beyond.university.student.model.service.DepartmentService;
import com.beyond.university.student.model.service.StudentService;
import com.beyond.university.student.model.vo.Department;
import com.beyond.university.student.model.vo.Student;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;	
//	private final StudentService studentService;
	
	@GetMapping("/department/search")
	public ModelAndView search(ModelAndView modelAndView, @RequestParam(required = false) String deptNo) {
		List<Department> departments = departmentService.getDepartments();
		
		if (deptNo != null) {
			Department department = departmentService.getDepartmentByDeptNo(deptNo);
//			List<Student> students = studentService.getStudentsByDeptNo(deptNo);
			
//			modelAndView.addObject("students", students);
			modelAndView.addObject("department",department);
		}
		
		modelAndView.addObject("departments",departments);
		modelAndView.setViewName("/department/search");
		
		
		return modelAndView;
	}


	
	

}
