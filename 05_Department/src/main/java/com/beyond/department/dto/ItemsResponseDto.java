package com.beyond.department.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ItemsResponseDto<T> extends BaseResponseDto<T>{
//    @Schema(description = "응답 코드", example = "200")
//    private int code;
//
//    @Schema(description = "응답 메시지", example = "OK")
//    private String message;
//
//    @Schema(description = "응답 데이터")
//    private List<Department> result;

    @Schema(description = "페이지 번호", example = "1")
    private int page;

    @Schema(description = "조회 결과 수", example = "1")
    private int numOfRows;

    @Schema(description = "전체 결과 수", example = "1")
    private int totalCount;

    public ItemsResponseDto(HttpStatus status, T result, int page, int numOfRows, int totalCount) {
        super(status, result);
//        this.code = status.value();
//        this.message = status.getReasonPhrase();
//        this.result = result;
//        this.page = page;
//        this.numOfRows = numOfRows;
//        this.totalCount = totalCount;
    }
}