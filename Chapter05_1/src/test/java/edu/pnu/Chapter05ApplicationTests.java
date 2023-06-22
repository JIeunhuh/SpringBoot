package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class Chapter05ApplicationTests {

	@Autowired
	private BoardRepository boardRep;
	
	@Test
	public void testInsertBoard() {
		Board b = new Board();
		b.setTitle("첫 번째 게시글");
		b.setWriter("tester");
		b.setContent("잘 등록되나요?");
		b.setCreateDate(new Date());
		b.setCnt(0L);
		
		boardRep.save(b);
	}

}
