package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.dao.MemberDAO1;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	private List<MemberVO> list;
	private MemberInterface memberDao;
	
	
	public MemberService() {
		memberDao = new MemberDAO1();
		
		}
	
	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}
	
	public MemberVO addMember(MemberVO m) {
		return memberDao.addMember(m);
	}
	
	public MemberVO updateMember(MemberVO m) {
		return memberDao.updateMember(m);
	}
	
	public int deleteMember(Integer id) {
		return memberDao.deleteMember(id);
	}
}
