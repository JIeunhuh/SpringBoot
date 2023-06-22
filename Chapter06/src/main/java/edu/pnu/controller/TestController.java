package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/home")
	public String home() {
		// ==> [WEB-INF/board/home.jsp]를 호출
		return "Home";
	}

	@GetMapping("/Home")// return되는 값이 없으면 boot에서 자동으로 getmapping()에 할당한 주소를 .jsp로 붙여서 호출?
	public void home1() {
		// ==> [WEB-INF/board/home.jsp]를 호출
//		return "Home";
	}
	
	@GetMapping("/model")
	public String model(Model model) {
		model.addAttribute("data","홍길동");
		return "model";
	}
}
