package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
// 로그인 인증 시도를 위
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
//	private final MemberRepository memberRepo;
	
	@Override
	// 로그인 인증 시도
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp)
			throws AuthenticationException {
		// 인증을 위한 토큰을 생성 -> 인증 요청 -> 성공하면 세션에 인증정보 등록
		ObjectMapper om = new ObjectMapper();
		// req의 body에 JSON으로 담겨오는 username / password 읽어서 Member 객체로 받아옴
		Member member;
		try {
			
			member = om.readValue(req.getInputStream(), Member.class);
			
			
//			이게 뭐하는 코드..? : 보안상의 이유로 id / password를 분리 하지 않는데 굳이 분리해서 id/pw 확인해보기
//			Optional<Member> option = memberRepo.findById(member.getUsername());
//			if(!option.isPresent()) {
//				log.info("Not Authenticated : Not Found User!");
//				return null;
//			}
			
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(),member.getPassword());
			Authentication auth = authenticationManager.authenticate(authToken);
			
			//읽어들인 정보가 정확한지 콘솔에 출력
			log.info("attemptAuthentication : [" + member.getUsername() + "]");
			return auth;
		
		} catch (Exception e) {
			log.info("NOT Authenticated : " + e.getMessage());
			//log.info("Not Authenticated : Not Found Password !");
		}
		return null;
	}

	@Override
	// 로그인 인증이 완료된 후 처리
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authRes) throws IOException, ServletException {
		
		User user = (User)authRes.getPrincipal();
		log.info("successfulAuthentication : " + user.toString());
		
		//JWT 생성
		String jwtToken = JWT.create()
							.withClaim("username",user.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
							.sign(Algorithm.HMAC256("edu.pnu.jwtkey"));
		
		// 응답 header에 "Authorization" 이라는 키를 추가해서 jwt를 설정
		// Bearer : JWT임을 나타내는 용어; Basic : "Basic " + Base64(username : password)
		resp.addHeader("Authorization", "Bearer " + jwtToken);
		
		chain.doFilter(req, resp);
	}
}
