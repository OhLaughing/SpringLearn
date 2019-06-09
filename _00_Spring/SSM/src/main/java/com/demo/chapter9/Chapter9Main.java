package com.demo.chapter9;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter9Main {
	public static void main(String[] args) {
		testIoC();
	}
	

	public static void testIoC() {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("spring-cfg.xml");
		JuiceMaker2 juiceMaker2 = (JuiceMaker2) ctx.getBean("juiceMaker2");
		System.out.println(juiceMaker2.makeJuice());
		ctx.close();
	}
}
