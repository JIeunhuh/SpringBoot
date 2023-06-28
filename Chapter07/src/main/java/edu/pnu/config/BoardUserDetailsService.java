package edu.pnu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository memberRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Member> option = memberRepo.findById(username);
		if (!option.isPresent())
			return null;
		Member member = option.get();
		System.out.println(member);
		return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
	}

}
