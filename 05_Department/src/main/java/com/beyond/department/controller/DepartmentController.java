package com.beyond.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.department.dto.DepartmentsResponseDto;
import com.beyond.department.service.DepartmentService;
import com.beyond.department.vo.Department;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController // controller랑 requestbody를 포함하는 어노테이션
@RequestMapping("/v1/department-service")
@Tag(name = "Department APIs", description = "학과 관련 API 목록")
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;
	
	/*
	 * HttpEntitiy
	 * 	- 스프링 프레임워크에서 제공하는 클래스로 HTTP 요청 또는 응답에 해당하는 HTTP헤더와 HTTP바디를 포함하는 클래스
	 * 
	 * ResponseEntity
	 * 	- HttpEntitiy를 상속하는 클래스로 HTTP응답 데이터를 포한하는 클래스이다.
	 * 	- 개발자가 직접 HTTP Header, Body, Status를 세팅하여 반환할 수 있다.
	 */
//	@GetMapping("/departments")
//	@Operation(summary = "학과 목록 조회", description = "전체 학과의 목록을 조회한다.")
//	public ResponseEntity<Map<String, Object>>  getDepartments() {
//		
//		Map<String, Object> map = new HashMap<>();
//		
//		
//		map.put("name", "홍길동");
//		map.put("age", 32);
//		map.put("hobby", new String[] {"축구","야구","농구"});
//		
//		return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
//	}
	
	@GetMapping("/departments")
	@Operation(summary = "학과 목록 조회", description = "전체 학과의 목록을 조회한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "ok", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "not found" , content = @Content(mediaType = "application/json"))
		}
	)
	@Parameters(
		value = {
				@Parameter(name = "page", description = "페이지 번호", example = "1"),
				@Parameter(name = "numOfRows", description = "한 페이지의 결과 수", example = "10"),
				@Parameter(name = "openYn", description = "개설 여부", example = "Y")
		}
	)	
	public ResponseEntity<DepartmentsResponseDto> getDepartments(
														@RequestParam int page, 
														@RequestParam int numOfRows,
														@RequestParam(required = false) String openYn) {
		
		int totalCount = departmentService.getTotalCount(openYn);
		List<Department> departments = departmentService.getDepartments(page, numOfRows, openYn);
		
		
		
		if (departments.size() > 0) {
			return ResponseEntity.ok(new DepartmentsResponseDto(HttpStatus.OK, departments, page, departments.size(), totalCount));	
		} else {
			return ResponseEntity.ok(new DepartmentsResponseDto(HttpStatus.NOT_FOUND, departments, page, departments.size(), totalCount));	
			
		}
	}

}










