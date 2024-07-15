package com.beyond.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CharacterAspect {
	/*
	 * 포인트 커트 표현식
	 * 	- 스프링 AOP에서 포인트 커트는 AspectJ의 포인트 커트 표현식을 이용해서 정의한다.
	 * 	- 스프링 AOP에서 지원되는 AspectJ의 포인트 커트 표현식
	 * 		execution([접근제한자]리턴타입 클래스이름.메소드이름([파라미터, ...]))
	 * 		  - 메소드 실해애에 대한 표현식이다
	 * 		  -  * : 모든 값을 표현한다.
	 *		  - .. : 0개 이상을 의미한다.
	 *		args()
	 *		  - 대상 메소드에 전달되는 매개값을 어드바이스에 전다하기 위한 표현식이다.
	 *		bean()
	 *		  - 어드바이스를 적용할 빈의 ID를 지정하는 표현식이다.
	 *		@annotation
	 *		  - 주어진 어노테이션을 갖는 조인포인트에 어드바이스가 적용된다.
	 */
	@Pointcut("execution(* com.beyond.aop.character.Character.quest(..)) && args(questName)")
	public void questPointcut(String questName) {
		
	}
	
//	@Before("execution(* com.beyond.aop.character.Character.quest(..))") // 포인트컷 지정자
//	@Before("questPointcut(questName)") // 포인트컷 지정자
	public void beforeQuest(String questName) {
		// 퀘스트를 수행하기 전에 필요한 작업을 수행
		
		System.out.println("퀘스트 준비 중 !");
	}
	
//	@After("execution(* com.beyond.aop.character.Character.quest(..))")
	public void afterQuest() {
		// 퀘스트를 수행하고 난 후 작업을 수행, 수행 결과에는 상관 없음
		
		System.out.println("퀘스트 수행 완료....!");
	}
	
//	@AfterReturning(pointcut = "questPointcut(questName)",
//					returning = "result")
	public void success(String result, String questName) {
		System.out.println("result : " + result);
		System.out.println(" 퀘스트 수행 완료 !");
	}
	
//	@AfterThrowing(pointcut = "questPointcut(questName)",
//					throwing = "exception")
	public void fail(Exception exception, String questName) {
		System.out.println("message : " + exception.getMessage());
		System.out.println("퀘스트 수행 중 에러가 발생했습니다....ㅠ");
	}
	
	@Around("execution(* com.beyond.aop.character.Character.quest(..))")
	public String around(ProceedingJoinPoint joinPoint) {
		String result = null;
		String questName = (String)joinPoint.getArgs()[0];
		try {
			// before 어드바이스에 대한 기능을 수행
			System.out.println(questName + " 퀘스트 준비 중 !");
			
			// 타겟 객체의 메소드를 실행시킨다.
//			joinPoint.proceed();
			
			// 타겟 객체의 메소드에 리턴값이 있는 경우
//			result = (String) joinPoint.proceed();
			result = (String) joinPoint.proceed(new Object[] {questName});
			
			// after-returning 어드바이스에 대한 기능을 수행
			System.out.println(result);
			System.out.println(questName + " 퀘스트 수행 완료 !");
			
		} catch (Throwable e) {
			// after-throwing 어드바이스에 대한 기능을 수행
			System.out.println(e.getMessage());
			System.out.println(questName + " 퀘스트 수행 중 에러가 발생했습니다....ㅠ");
		}
		
		return result;
		
	}
	
}
