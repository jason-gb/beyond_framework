package com.beyond.mvc.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beyond.mvc.member.model.service.MemberService;
import com.beyond.mvc.member.model.vo.Member;

@Controller
public class MemberController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
// 요청 시 사용자의 파라미터를 전송받는 방법
// 1. HttpServletRequest 객체를 통해서 전송받기
	
//	@RequestMapping(value = "/login", method = {RequestMethod.POST})
//	@GetMapping("/login")
//	@PostMapping("/login")
//	public String login(HttpServletRequest request) {
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		
//		log.debug("login() 호출 - {} {}", userId, userPwd);
//		
//		return "redirect:/";
//	}

	// 2. @RequestParam 어노테이션을 통해서 전송받기
	// - 스프링에서는 간편하게 파라미터를 받아올 수 있는 방법을 제공한다.
	// - 단, 매개변수의 이름과 파라미터의 name 속성의 값이 동일하면 @RequsetParam을 생략할 수 있다.
	// - 실제 존재하지 않는 파라미터를 받으려고 하면 에러가 발생한다. (required = true)
	// - required = false로 변경하면 에러가 아닌 null 값을 넘겨준다.
	// - defaultValue 속성을 사용하면 파라미터가 존재하지 않을 경우 기본값을 지정할 수 있다.
	
//	@PostMapping("/login")
//	public String login(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd) {
//	public String login(@RequestParamString userId, @RequestParam String userPwd) {
//	public String login(@RequestParam String userId, 
//									@RequestParam String userPwd, 
////									@RequestParam(required = false) String address) {
//									@RequestParam(defaultValue = "서울특별시") String address) {
//		
//		log.debug("login() 호출 - {} {} {}",new Object[] {userId, userPwd, address});
//		
//		return "redirect:/";
//	}
//	3. 객체 타입으로 파라미터를 전송받는 방법
//		- 요청 파라미터가 많은 경우 객체 타입으로 파라미터를 넘겨받는 방법이다.
//		- 단, Setter가 존재해야 한다.
//	@PostMapping("/login")
//	public String login(Member member) {
//		
//		log.debug(member.toString());
//	
//		return "redirect:/";
//	}
	
//	4. @PathVariable 어노테이션으로 전송받는 방법
//		- URL 패스상에 있는 특정 값을 가져오기 위해 사용하는 방법이다.
//		- REST API를 사용할 때 요청 URL 상에서 필요한 값을 가져오는 경우에 주로 사용한다.
//		- 매핑 URL에 {} 안에 있는 변수명과 매개변수명이 동일하다면 @pathVariable의 괄호는 생략이 가능하다.
//	@GetMapping("/member/{no}")
//	public String findMember(@PathVariable("meNo") String no) {
//	public String findMember(@PathVariable("meNo") int no) {
//	public String findMember(@PathVariable int no) {
//		
//		log.debug("findMember()호출 - {}", no);
//		
//		return "redirect:/";
//	}

//	로그인 구현
//	1. HttpSession과 Model 객체 사용
//		- Model 객체는 컨트롤러에서 데이터를 뷰로 전달하고자 할 때 사용하는 객체이다.
//		- Model 객체는 전달하고자 하는 데이터를 맵 형식(key,value) 로 담을 수 있다
//		- Mode 객체의 scope는 Request이다.
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public String login(@RequestParam String userId, 
			@RequestParam String userPwd, 
			HttpSession session,
			Model model) {
	
		log.debug("login() 호출 - {} {}", userId, userPwd);
		
		Member loginMember = service.login(userId, userPwd);
		
		if (loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			
			return "redirect:/";
		} else {
			model.addAttribute("msg","아이디나 패스워드가 틀림");
			model.addAttribute("location","/");
			
			return "common/msg";
		}
		
	
	}
	
	// 로그아웃 구현
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	
	
}                        

