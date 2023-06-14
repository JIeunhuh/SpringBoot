package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberService memberService;
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO m) {
		return memberService.addMember(m);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO m) {
		return memberService.updateMember(m);
	}
	
	@DeleteMapping("/member/{id}")
	public int deleteMember(@PathVariable Integer id) {
		return memberService.deleteMember(id);
	}
}
