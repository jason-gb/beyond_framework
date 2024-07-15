package com.beyond.aop.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beyond.aop.weapon.Weapon;

import lombok.Data;

@Data
@Component
public class Character {
	@Value("홍길동")
    private String name;

	@Value("77")
    private int level;

	@Autowired
//	@Qualifier("sword")
    private Weapon weapon;

	public String quest(String questname) {
		
//		if(true) {
//			throw new RuntimeException("Quest 처리 중 예외 발생");
//		}
//		
//		System.out.println(questname + " 퀘스트를 진행 중.");
		
		return questname + " 퀘스트 진행 중";
	}
   
    
}