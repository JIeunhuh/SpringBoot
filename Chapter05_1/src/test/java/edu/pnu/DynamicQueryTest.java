package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;

	// queryDsl 이용한 동적 쿼리
	
	private void test(String searchCondition, String searchKeyword) {

		BooleanBuilder bd = new BooleanBuilder();

		QBoard qboard = QBoard.board;

		if (searchCondition.equals("TITLE")) {
			// select b from Board b where b.title like '%'||searchKeyword||'%'
			bd.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if (searchCondition.equals("CONTENT")) {
			// select b from Board b where b.content like '%'||searchKeyword||'%'
			bd.and(qboard.content.like("%" + searchKeyword + "%"));
		}
		Iterable<Board> list = boardRepo.findAll(bd);
		for (Board b : list) {
			System.out.println("--->" + b);
		}
	}

	// map이용해서 title, content 다 나오게 만들기
	// mission
	//1. title이 "%title1%"인거 나오게 만들기 / 2. 
	private void test1(Map<String, String> map) {

		BooleanBuilder bd = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		Set<String> keys = map.keySet();
		for (String key : keys) {
			if (key.equals("TITLE")) {
				bd.and(qboard.title.like("%" + map.get("TITLE") + "%"));
			} else if (key.equals("CONTENT")) {
				bd.and(qboard.content.like("%" + map.get("CONTENT") + "%"));
			} else if (key.equals("CNT")) {
				bd.and(qboard.cnt.gt(Integer.parseInt(map.get("CNT"))));
			}
		}
		Iterable<Board> list = boardRepo.findAll(bd);
		for (Board b : list) {
			System.out.println("--->" + b);
		}

	}

	  
	@Test
	public void testDynamicQuery() {
		//test("TITLE", "Title1");
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "Title1");
		map.put("CONTENT", "Content12");
		test1(map);
	}
}
