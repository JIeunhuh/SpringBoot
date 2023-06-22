package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/Hello")
	public void Helllo(Model model) {
		model.addAttribute("greeting","Hello 타임리프 *^^*");
	}
}
