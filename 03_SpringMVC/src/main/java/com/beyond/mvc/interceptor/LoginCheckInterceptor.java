package com.beyond.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 	인터셉터
 * 		- 컨트롤러에 들어오는 요청과 응답을 가로채는 역할을 한다.
 * 		- 인터셉터를 구현하기 위해서는 HandlerInterceptor 인터페이스를 구현해야 한다.
 * 
 * 	필터와 차이점
 * 		- 필터는 서블릿 실행 전에 요청과 응답을 가로챈다. (web.xml)
 * 		- 인터셉터는 디스패처 서블릿 수행 후 컨트롤러에 요청을 넘기기 전에 실행된다.(servlet-context.xml)
 * 
 */

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("prehandle() - 호출");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("posthandle() - 호출");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion() - 호출");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
}
