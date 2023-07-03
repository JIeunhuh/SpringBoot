package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	PasswordEncoder encoder;
	public void save(Member member) {
//		member.setRole("ROLE_MEMBER");
//		member.setEnabled(true);
//		member.setPassword(encoder.encode(member.getPassword()));
	
		memberRepo.save(Member.builder()
				.username(member.getUsername())
				.password(encoder.encode(member.getPassword()))
				.role("ROLE_MEMBER")
				.enabled(true)
				.build());
	}
}
