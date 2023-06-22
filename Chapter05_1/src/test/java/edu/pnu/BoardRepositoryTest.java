package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRep;

	//@Test
	public void testInsertBoard() {
		Board b = new Board();
		for (int i = 1; i <= 10; i++) {
			b = Board.builder()
					.title("Title" + i)
					.writer("tester" + i)
					.content("Content" + i)
					.createDate(new Date())
					.cnt(0L)
					.build();
			boardRep.save(b);
		}
		
	}

	//@Test
	public void testGetBoard() {
		Board board = boardRep.findById(10L).get();
		System.out.println(board);
	}

	//@Test
	public void testUpdateBoard() {
		Board board = boardRep.findById(1L).get();
		System.out.println("===1번 게시글 제목 수정===");
		board.setTitle("제목을 수정했습니다.");
		boardRep.save(board);
	}
	//@Test
	public void testDeleteBoard() {
		boardRep.deleteById(1L);
	}

	//@Test
	public void testFindAll() {
		List<Board> list = boardRep.findAll();
		System.out.println("모든 데이터를 출력합니다.");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	//@Test
	public void testQueryAnnotationTest1() {
		//select b from Board b where b.title like '%Title1%' order by b.seq desc;
		List<Board> list = boardRep.queryAnnotationTest1("Title1");
		for(Board b: list) {
			System.out.println(b.toString());
		}
	}
	
	//@Test
	public void testQueryAnnotationTest2() {
		//select b from Board b where b.title like '%Title2%' order by b.seq desc;
		List<Board> list = boardRep.queryAnnotationTest1("Title2");
		for(Board b: list) {
			System.out.println(b.toString());
		}
	}
	
	//@Test
	public void testQueryAnnotationTest3() {
		//select b from Board b where b.title like '%Title2%' order by b.seq desc;
		List<Object[]> list = boardRep.queryAnnotationTest3("Title2");
		for(Object[] b: list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
	//@Test
	public void testQueryAnnotationTest4() {
		//select b from Board b where b.title like '%Title10%' order by b.seq desc;
		List<Object[]> list = boardRep.queryAnnotationTest4("Title10");
		for(Object[] b: list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
	@Test
	public void testQueryAnnotationTest5() {
		//Pageable paging = PageRequest.of(0,3,Sort.Direction.DESC,"seq"); //query문이 우선 됨(sort.direction.desc 무시 됨)
		Pageable paging = PageRequest.of(5,5); 
		List<Board> list = boardRep.queryAnnotationTest5(paging);
		for(Board b : list) {
			System.out.println(b);
		}
	}
}
