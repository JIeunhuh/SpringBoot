package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {

	// @RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Board b = new Board();
			b = Board.builder().seq(i).title("게시판 프로그램 테스트").writer("도우너").content("게시판 프로그램 테스트입니다..")
					.createDate(new Date()).cnt(0L).build();
			list.add(b);
		}
		model.addAttribute("boardList", list);
		return "getBoardList";
	}

	@Autowired
	BoardRepository boardRepo;
	
	@Autowired
	BoardService boardService;

	@GetMapping("/getBoardList")
	public String getBoardList1(Model model) {

		model.addAttribute("boardList", boardService.getBoardList());
		return "getBoardList";
	}

	@GetMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		return "insertBoard";
	}
//	//error
//		@GetMapping("/findPage")
//		public List<Board> findPage(Integer pageNum ,Integer size) {
//			Pageable paging = PageRequest.of(pageNum, size);
//			Page<Board> page = boardRep.findAll(paging);
//			return page.getContent();
//		}

	@PostMapping("/insertBoard")
	public String insertBoardPost(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		boardService.insertBoard(board);
		return "redirect:getBoardList"; // redirect 설정 안해주면 default로 forward 방식으로 들어감?호출?
	}

	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Long seq, Model model) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		return "getBoard";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) {
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}

	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		List<Board> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList",boardList);
		return "getBoardList";
	}
}
