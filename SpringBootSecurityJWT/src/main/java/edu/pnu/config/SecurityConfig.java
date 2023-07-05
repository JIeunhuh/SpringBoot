package edu.pnu.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.config.auth.JWTAuthorizationFilter;
import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private AuthenticationConfiguration authConfig; 
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new PasswordEncoderFactories.create;
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()); //js 호출가능
		http.cors(cors->cors.disable()); //react에서 호출가능
		
		http.authorizeHttpRequests(security -> {
			security.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll();
		});
		
		// form을 이용한 로그인을 사용하지 않겠다는 설정
		http.formLogin(frmLogin -> frmLogin.disable());
		
		// security session을 만들지 않겠다고 설정(로그인 해서 request -> response 되는 순간 토큰이 사라짐?)
		http.sessionManagement(ssmg->ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager()));
		http.addFilter(new JWTAuthorizationFilter(authConfig.getAuthenticationManager(), memberRepo));
		return http.build();
	}
}
