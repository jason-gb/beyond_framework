package com.beyond.department.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.beyond.department.vo.Department;

import lombok.Getter;

@Getter
public class DepartmentsResponseDto {
	private int code;
	
	private String message;
	
	private List<Department> result;
	
	private int page;
	
	private int numOfRows;
	
	private int totalCount;
	
	public DepartmentsResponseDto(HttpStatus status ,List<Department> result, int page, int numOfRows, int totalCount) {
		this.code = status.value();
		this.message = status.getReasonPhrase();
		this.result = result;
		this.page = page;
		this.numOfRows = numOfRows;
		this.totalCount = totalCount;
	}
}
