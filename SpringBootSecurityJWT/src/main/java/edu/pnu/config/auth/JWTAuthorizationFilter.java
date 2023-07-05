package edu.pnu.config.auth;

// 코드 안틀린것 같은데 오류나면 import에 문제있는지 확인해보기 (싹다 지웠다가 새로 해보기)
import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	
	private MemberRepository memberRepo;
		
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepo) {
		super(authenticationManager);
		this.memberRepo = memberRepo;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		String srcToken = req.getHeader("Authorization");
		if(srcToken == null || !srcToken.startsWith("Bearer ")) {
			chain.doFilter(req, resp);
			return ;
		}
		String jwtToken = srcToken.replace("Bearer ","");
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwtkey")).build().verify(jwtToken).getClaim("username").asString();
		Optional<Member> opt = memberRepo.findById(username);
		if(!opt.isPresent()) {
			chain.doFilter(req, resp);
			return;
		}
		
		Member findMember = opt.get();
		User user = new User(findMember.getUsername(), findMember.getPassword(), findMember.getAuthorities());
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, resp);
		
	}

}
