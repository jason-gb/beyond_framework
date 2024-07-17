package com.beyond.university.student.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.beyond.university.student.model.vo.Student;

@Mapper
public interface StudentMapper {

	List<Student> selectAllByDeptNo(@Param("deptNo") String deptNo);

	Student selectStudentByNo(String sno);

	int insertStudent(Student student);
	
}
