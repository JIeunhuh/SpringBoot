package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Component
public class MemberInitialize implements ApplicationRunner {

	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder; //decoding 안됨 -> 역으로 뒤집어서 암호화된 pw를 원래 형식?으로 보이게 만드는 거
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		memberRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("abcd")) 
				.role("ROLE_MEMBER")// spring boot에서 사용하는 기본 규칙 (ROLE_이름) ; 내부프로세서에서 ROLE_앞에 붙여서 돌림
				.enabled(true)
				.build());
		
		memberRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role("ROLE_MANAGER")
				.enabled(true)
				.build());
		
		memberRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role("ROLE_ADMIN")
				.enabled(true)
				.build());
	
	}

		
}
