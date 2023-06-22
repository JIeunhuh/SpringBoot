package edu.pnu.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {

	@Autowired
	BoardRepository boardRep;

	// 1번 문제
	@GetMapping("/getBoardByTitleLike")
	public List<Board> getBoardByTitleLike(String title) {
		return boardRep.findByTitleLike("%"+title+"%");
	}
	
	// 2번 문제
	@GetMapping("/getBoardByTitleLikeAnd")
	public List<Board> getBoardByTitleLikeAnd(String title, Long cnt){
		return boardRep.findByTitleLikeAndCntGreaterThan("%"+title+"%", cnt);
	}

	// 3번 문제
	@GetMapping("/getBoardByCntBetween")
	public List<Board> getBoardByCntBetween(Long cnt1, Long cnt2){
		return boardRep.findByCntBetweenOrderBySeqAsc(cnt1, cnt2);
	}
	
	// 4번 문제
	@GetMapping("getBoardByTitleLikeAndContentLike")
	public List<Board> getBoardByTitleLikeAndContentLike(String title, String content){
		return boardRep.findByTitleLikeOrContentLikeOrderBySeqDesc("%"+title+"%", "%"+content+"%");
	}
	
	//error
	@GetMapping("/findPage")
	public List<Board> findPage(Integer pageNum ,Integer size) {
		Pageable paging = PageRequest.of(pageNum, size);
		Page<Board> page = boardRep.findAll(paging);
		return page.getContent();
	}
	
	
	@GetMapping("/getBoard")
	public Optional<Board> getBoard(Long id) {
		return boardRep.findById(id);
	}
	
	@GetMapping("/getBoardLists")
	public List<Board> getBoardLists() {
		return boardRep.findAll();
	}

	@PostMapping("/insertBoard")
	public Board insertBoard(Board b) {
		if (b.getCreateDate() == null)
			b.setCreateDate(new Date());
		return boardRep.save(b);
	}
	
	//json 형식으로 파일 요청할때
//	@PostMapping("/insertBoardjson")
//	public Board insertJsonBoard(@RequestBody Board b) {
//		if (b.getCreateDate() == null)
//			b.setCreateDate(new Date());
//		return boardRep.save(b);
//	}

	@PutMapping("/updateBoard")
	public Board updateBoard(Board b) {
		Board b1 = boardRep.findById(b.getSeq()).get();
		if(b.getTitle()!=null) {
			b1.setTitle(b.getTitle());
		}
		if(b.getWriter()!=null) {
			b1.setWriter(b.getWriter());
		}
		if(b.getContent()!=null) {
			b1.setContent(b.getContent());
		}
		return boardRep.save(b1);
	}

	@DeleteMapping("/deleteBoard")
	public void deleteBoard(Long id) {
		boardRep.deleteById(id);
		System.out.println(id+"가 삭제되었습니다.");
	}
}
