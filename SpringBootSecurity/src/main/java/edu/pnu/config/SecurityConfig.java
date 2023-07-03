package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.service.BoardOauth2UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	BoardOauth2UserDetailsService oauthService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화 (JS 에서 호출 가능)
		http.cors(cors -> cors.disable()); // CORS 보호 비활성화 (React에서 호출가능)

		// 웹페이지 접근 권한 설정
		http.authorizeHttpRequests(security -> {
			security.requestMatchers("/member/**").authenticated() // ;/member/** => member/뒤에 있는 모든 url 다 가지고 옴
					.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // -> manager,admin 권한 사용자만 접근가능
					.requestMatchers("/admin/**").hasRole("ADMIN") // -> admin 권한 사용자만 접근가능
					.anyRequest().permitAll();
			try {
				http.oauth2Login(oauth2 ->
				{oauth2.loginPage("/login")
					.userInfoEndpoint(uend->uend.userService(oauthService))
					.defaultSuccessUrl("/loginSuccess",true);
				
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//로그인 처리
			try {
				http.formLogin(frmLogin -> {
					frmLogin.loginPage("/login")
						.defaultSuccessUrl("/loginSuccess", true);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//로그아웃 처리
			try {
				http.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
				http.logout(logt -> {
					logt.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/login");
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return http.build();
	}
}
