package com.beyond.aop.aspect;

import java.util.Iterator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.beyond.aop.annotation.Repeat;

@Aspect
@Component
public class WeaponAspect {
//	@Around("execution(* com.beyond.aop.weapon.*.attack())")
//	@Around("execution(* com.beyond.aop.weapon.Weapon.attack()) && !bean(sword)")
//	@Around("execution(* com.beyond.aop.weapon.Weapon.attack()) && @annotation(com.beyond.aop.annotation.NoLogging)")
//	public String attackAdvice(ProceedingJoinPoint joinPoint) {
//		String result = null;
//		
//		try {
//			
//			System.out.println("공격을 준비 중입니다.");
//
//			result = (String) joinPoint.proceed();
//			
//			System.out.println(result);
//			System.out.println("공격을 성공했습니다.");
//		
//		} catch (Throwable e) {
//			System.out.println("공격에 에러가 생겼습니다.");
//		}
//		
//		return result;
//	}
	
	@Around("@annotation(com.beyond.aop.annotation.Repeat)")
	public String attackAdvice(ProceedingJoinPoint joinPoint) {
		String result = null;
		MethodSignature signature = (MethodSignature)joinPoint.getSignature(); // 메소드 선언부를 의미
		Repeat repeat = signature.getMethod().getAnnotation(Repeat.class);
		
		try {
			
			System.out.println("공격을 준비 중입니다.");

			for(int i = 0 ; i < repeat.count() ; i++) {
				result = (String) joinPoint.proceed();
				System.out.println(result);				
			}
			
			System.out.println("공격을 성공했습니다.");
		
		} catch (Throwable e) {
			System.out.println("공격에 에러가 생겼습니다.");
		}
		
		return result;
	}
}
