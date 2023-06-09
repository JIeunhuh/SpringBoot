package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;



public class MemberService {
	
	private MemberDAO memberDao;
	
	public MemberService() {
		memberDao = new MemberDAO();
	}
	
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
