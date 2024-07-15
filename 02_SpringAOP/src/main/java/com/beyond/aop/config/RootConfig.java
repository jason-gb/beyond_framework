package com.beyond.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.beyond.aop") // 루트 주소 설정
@EnableAspectJAutoProxy // 프록시 개채 자동 생성
public class RootConfig {
	
}
