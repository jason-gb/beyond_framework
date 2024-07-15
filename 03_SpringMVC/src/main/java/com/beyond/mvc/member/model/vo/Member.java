package com.beyond.mvc.member.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int no;

    private String id;

    private String password;
    
    @JsonIgnore
    private String role;

    private String name;

    @JsonIgnore
    private String phone;

    private String email;

    @JsonIgnore
    private String address;

    private String hobby;

    @JsonIgnore
    private String status;

    private Date enrollDate;

    private Date modifyDate;
	
}
