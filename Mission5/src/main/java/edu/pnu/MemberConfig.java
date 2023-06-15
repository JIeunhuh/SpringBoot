package edu.pnu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.dao.MemberDAOH2;
import edu.pnu.dao.MemberInterface;
import edu.pnu.service.MemberService;

@Configuration
public class MemberConfig {

	@Bean
	public MemberService memberService() { //객체 생성만 해줌 
		return new MemberService();
	}

}
