package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailService;
	
	@Bean
	public BCryptPasswordEncoder encoder() { // 암호화 라이브러리
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.authorizeHttpRequests(auth -> {
//
////			auth.requestMatchers("/").permitAll(); // antMatchers -> requestMatchers()로 바뀜
////			auth.requestMatchers("/member/**").authenticated() // 인증이 되어야 함 ; 로그인을 해야 접속 할 수 있음
////					.requestMatchers("/manager/**").hasRole("MANAGER") // 권한이 있어야 함
////					.requestMatchers("admin/**").hasRole("ADMIN");
//
//			
//			
//		});
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasRole("MANAGER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll();
		});
		
		http.csrf(csrf -> csrf.disable());

//		http.formLogin(form -> {
//			form.loginPage("/login");
//			form.defaultSuccessUrl("/");
//		}); // form login을 사용

		http.formLogin(f->{
			f.loginPage("/login")
			.defaultSuccessUrl("/loginSuccess",true);
		});
		http.exceptionHandling(excp->{
			excp.accessDeniedPage("/accessDenied");
		});
		
		http.userDetailsService(boardUserDetailService);
		
		return http.build();
	}
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("manager")
			.password("{noop}manager123")
			.roles("MANAGER");
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}admin123")
			.roles("ADMIN");
		
	}
}
