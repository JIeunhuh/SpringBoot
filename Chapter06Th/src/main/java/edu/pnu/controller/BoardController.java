package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {

	//@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> list = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			Board b = new Board();
			b = Board.builder()
					.seq(i)
					.title("게시판 프로그램 테스트")
					.writer("도우너")
					.content("게시판 프로그램 테스트입니다..")
					.createDate(new Date())
					.cnt(0L)
					.build();
			list.add(b);
		}
		model.addAttribute("boardList",list);
		return "getBoardList";
	}
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/getBoardList")
	public String getBoardList1(Model model) {
		model.addAttribute("boardList",boardService.getBoardList());
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList"; //redirect 설정 안해주면 default로 forward 방식으로 들어감?호출?
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board",board);
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
