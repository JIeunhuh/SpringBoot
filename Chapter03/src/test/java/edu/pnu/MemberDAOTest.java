package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberDAOTest {

	@Test
	@Order(1)
	public void insertMemberTest() {
		MemberDAO dao = new MemberDAO();

		for (int i = 1; i <= 10; i++) {
			//builder 이용
			dao.insertMember(
				Member.builder()
				.name("name"+i)
				.age(20+i)
				.nickname("nickname"+i)
				.build());
			
			//기본 생성자를 이용한 방법
//			Member m = new Member();
//			m.setAge(20+i);
//			dao.insertMember(m);
//			// ~~ 요런식으로 
			
			//parameter를 이용한 방법
		//	dao.insertMember(new Member(-1,"name"+i,20+i,"nickname"+i));
		}
	}
		
	@Test
	@Order(2)
	public void selectAllMemberTest() {
		MemberDAO dao = new MemberDAO();
		List<Member> list = dao.getMembers();
		for(Member m : list) {
			System.out.println(m);
		}
	}
	
}
