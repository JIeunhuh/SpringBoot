package edu.pnu.service;

import org.springframework.stereotype.Service;

@Service
public class TestService1 {

	public TestService1() { //@Service 주석 달면 해당 문자열 출력안됨
		System.out.println("TestService1");
	}
	
	public String test() {
		return test();
	}
	
}
