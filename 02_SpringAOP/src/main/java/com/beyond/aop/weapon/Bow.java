package com.beyond.aop.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.beyond.aop.annotation.Repeat;

@Primary
@Component
public class Bow extends Weapon {

	public Bow(@Value("불멸의 철갑궁") String name) {
		super(name);
	}

	@Override
	@Repeat(count = 4)
	public String attack() {
		
		return "활을 쏜다.";
	}

	@Override
	public String toString() {
		return "Bow [name=" + name + "]";
	}

}
