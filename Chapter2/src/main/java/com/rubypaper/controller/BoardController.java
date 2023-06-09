package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

//해당 class를 scanning해서 인스턴스 생성 -> container에 저장 ; RestController annotation의 역할

@RestController // 어노테이션 사용 ; @Controller & @RestController
public class BoardController {// extend로 class 상속받지 않음
	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("Board Controller가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}

	// boot가 객체를 생성해줌
	@GetMapping("/hello")
	public String hello1(String name) {
		return "get Hello ; " + name;
	}

	@PostMapping("/hello")
	public String hello2(String name) {
		return " Post Hello ; " + name;
	}

	@PutMapping("/hello")
	public String hello3(String name) {
		return "Put Hello ; " + name;
	}

	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "Delete Hello ; " + name;
	}

	@GetMapping("/getBoard") //json 형태로 넘어감
	public BoardVO getBoard() {
		// 1. class명.builer() 이용해서 체인 코딩; 줄줄이 값만 넣어줌
		return BoardVO.builder().seq(1).title("test title").writer("tester1").content("text...").createDate(new Date())
				.cnt(0).build();
	}
	// 2. 생성자에 파라미터 다 집어 넣어서 만들기
	// 3.setter 이용

	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> board = new ArrayList<>();
		BoardVO b = new BoardVO();
		b.setSeq(1);
		b.setTitle("test2");
		b.setWriter("tester3");
		b.setContent("txt...");
		b.setCreateDate(new Date());
		b.setCnt(2);
		board.add(b);
		
		return board;
	}

}
