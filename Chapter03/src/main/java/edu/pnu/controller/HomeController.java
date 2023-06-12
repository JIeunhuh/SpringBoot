package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j //lombok이 제공해주는 logging engine
public class HomeController {

	public HomeController() {
		System.out.println("HomeController가 생성되었습니다.");
		
		//화면에 찍힘
		log.error("HomeController가 생성되었습니다.");
		log.warn("HomeController가 생성되었습니다.");
		log.info("HomeController가 생성되었습니다.");
		
		//화면에 안 찍힘(console)
		log.trace("HomeController가 생성되었습니다.");
		log.debug("HomeController가 생성되었습니다.");
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
}
