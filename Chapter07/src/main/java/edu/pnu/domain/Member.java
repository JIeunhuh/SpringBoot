package edu.pnu.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
public class Member {
	@Id
	private String username;
	private String password;
	private String role; //MEMBER , MANAGER , ADMIN
	private boolean enabled;
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
		
		// 방법 1.
		return AuthorityUtils.createAuthorityList(role); //list로 만들어서 return 함(default로 arraylist 생성)
		
		// 방법 2.
//		Collection<GrantedAuthority> list = new ArrayList<>();
//		list.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// TODO Auto-generated method stub
//				return role;
//			}
//		});
//		return list;
	}
}
