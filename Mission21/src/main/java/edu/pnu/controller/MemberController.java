package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
// 4번 ; lombok annotation이용 (이것만 달아주면 됨)
@RequiredArgsConstructor
public class MemberController {

// 1번 ; 필드에서 설정하기
//	@Autowired
	private MemberService memberService;
//	
//	public MemberController() {
//	System.out.println("MemberController가 생성되었습니다.");
//	log.info("MemberController가 생성되었습니다.");
//	//memberService = new MemberService();
//	}
//	
	
	// 2번 ; 생성자에서 설정하기
//	@Autowired
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	// 3번 ; setter이용
//	@Autowired
//	private void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
//	
	
	@GetMapping("/member/{id}") //{id} = 경로 변수 & select ~ 
	public Member getMember(@PathVariable Long id) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/members") //{id} = 경로 변수 & select all
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	@PostMapping("/member") // insert into
	public Member insertMember(Member m) {
		System.out.println("insert member = "+ m);
		return memberService.insertMember(m);
	}
	
	@PutMapping("/member") //update
	public Member updateMember(Member m) {
		return memberService.updateMember(m);
	}
	
	@DeleteMapping("/member/{id}") //delete
	public int deleteMember(@PathVariable Long id) {
		return memberService.deleteMember(id);
	}
}
