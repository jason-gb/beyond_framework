package com.beyond.university.student.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beyond.university.student.model.mapper.StudentMapper;
import com.beyond.university.student.model.vo.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	private final StudentMapper studentMapper;

	@Override
	public List<Student> getStudentsByDeptNo(String deptNo) {
		
		
		return studentMapper.selectAllByDeptNo(deptNo);
	}

	@Override
	public Student getStudentsByNo(String sno) {
		
		return studentMapper.selectStudentByNo(sno);
	}

	@Override
	public int save(Student student) {
		
		return studentMapper.insertStudent(student);
	}

}
