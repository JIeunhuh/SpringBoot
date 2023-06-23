package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class DataInitializeTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		memberRepo.save(Member.builder()
				.id("member1")
				.name("둘리")
				.password("member111")
				.role("ROLE_USER")
				.build());
		
		memberRepo.save(Member.builder()
				.id("member2")
				.name("도우너")
				.password("member222")
				.role("ROLE_ADMIN")
				.build());	
	
		for(int i=1; i<3; i++) {
			boardRepo.save(Board.builder()
					.writer("둘리")
					.title("둘리가 등록한 게시글"+i)
					.content("둘리가 등록한 게시글 내용"+i)
					.createDate(new Date())
					.build());
		}

		for(int i=1; i<3; i++) {
			boardRepo.save(Board.builder()
					.writer("도우너")
					.title("도우너가 등록한 게시글"+i)
					.content("도우너가 등록한 게시글 내용"+i)
					.createDate(new Date())
					.build());
			}
		}
	
}
