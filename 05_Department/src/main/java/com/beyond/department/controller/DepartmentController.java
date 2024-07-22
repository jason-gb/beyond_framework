package com.beyond.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.department.dto.BaseResponseDto;
import com.beyond.department.dto.DepartmentRequestDto;
import com.beyond.department.dto.ItemsResponseDto;
import com.beyond.department.service.DepartmentService;
import com.beyond.department.vo.Department;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController // controller랑 requestbody를 포함하는 어노테이션
@RequestMapping("/v1/department-service")
@Tag(name = "Department API", description = "학과 관련 API 목록")
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
			@ApiResponse(responseCode = "200", description = "ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemsResponseDto.class))),
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
	public ResponseEntity<ItemsResponseDto<List<Department>>> getDepartments(
														@RequestParam int page, 
														@RequestParam int numOfRows,
														@RequestParam(required = false) String openYn) {
		
		int totalCount = departmentService.getTotalCount(openYn);
		List<Department> departments = departmentService.getDepartments(page, numOfRows, openYn);
		
		
		
		if (departments.size() > 0) {
			return ResponseEntity.ok(new ItemsResponseDto<>(HttpStatus.OK, departments, page, departments.size(), totalCount));	
		} else {
			return ResponseEntity.ok(new ItemsResponseDto<>(HttpStatus.NOT_FOUND, departments, page, departments.size(), totalCount));	
			
		}
	}
	
	@GetMapping("/departments/{department-no}")
	@Operation(summary = "학과 상세 조회", description = "학과 번호로 학과 상세 정보를 조회한다.")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "ok", content = @Content(mediaType = "application/json")),
					@ApiResponse(responseCode = "404", description = "not_found", content = @Content(mediaType = "application/json"))
			}
	)
	public ResponseEntity<BaseResponseDto<Department>> getDepartmentByDeptNo(
			@Parameter(description = "학과 번호", example = "001") @PathVariable("department-no") String deptNo) {
		Department department = departmentService.getDepartmentByDeptNo(deptNo);
		
		if (department != null) {
			return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
		} else {
			return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.NOT_FOUND, department));			
		}
		
	}
	
	@PostMapping("/departments")
	@Operation(summary = "학과 등록", description = "학과 정보를 JSON으로 받아서 등록한다.")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "201", description = "CREATE", content = @Content(mediaType =  MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			}
	)
	public ResponseEntity<BaseResponseDto<Department>> create(
			@RequestBody DepartmentRequestDto requestDto) {
		Department department = new Department(requestDto);
		
		departmentService.save(department);
		
		return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.CREATED, department));
	}
	
	@PutMapping("/departments/{department-no}")
	@Operation(summary = "학과 수정", description = "학과 번호와 정보를 JSON으로 받아 수정한다.")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			}
	)
	public ResponseEntity<BaseResponseDto<Department>> update(
			@Parameter(description = "학과 번호", example = "001") @PathVariable("department-no") String deptNo,
			@RequestBody DepartmentRequestDto requestDto ){
		
		Department department = departmentService.getDepartmentByDeptNo(deptNo);
		
		if (department != null) {
			department.setDepartmentRequestDto(requestDto);
			departmentService.save(department);
			
			return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
		} else {
			return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.NOT_FOUND, department));			
		}
		
	}
	
	
	@DeleteMapping("/departments/{department-no}")
	@Operation(summary = "학과 삭제", description = "학과 번호로 해당 학과를 삭제한다.")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			}
	)
	public ResponseEntity<BaseResponseDto<Department>> delete(
			@PathVariable("department-no") String deptNo ){
		
		Department department = departmentService.getDepartmentByDeptNo(deptNo);
		
		if (department != null) {
			departmentService.delete(deptNo);
			
			
//			return ResponseEntity.noContent().build();
			return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
		} else {
			return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.NOT_FOUND, department));			
		}
	}
	
	

}











