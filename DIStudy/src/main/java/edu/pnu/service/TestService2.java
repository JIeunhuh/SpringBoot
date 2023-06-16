package edu.pnu.service;

import org.springframework.stereotype.Service;

@Service
public class TestService2 {

	public TestService2() { //@Service 주석 달면 해당 문자열 출력안됨
		System.out.println("TestService2");
	}
	
}
