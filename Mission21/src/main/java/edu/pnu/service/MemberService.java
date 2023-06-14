package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;


@Service
public class MemberService {
	
	@Autowired //객체 생성 하면 안됨
	private MemberDAO memberDao;
	
	public Member getMember(Long id) {
		return memberDao.getMember(id);
	}
	
	public List<Member> getMembers() {
		return memberDao.getMembers();
	}
	
	public Member insertMember(Member m) {
		return memberDao.insertMember(m);
	}
	
	public Member updateMember(Member m) {
		return memberDao.updateMember(m);
	}
	
	public int deleteMember(Long id) {
		return memberDao.deleteMember(id);
	}
}
