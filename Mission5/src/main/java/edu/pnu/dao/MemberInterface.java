package edu.pnu.dao;

import java.util.Map;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	Map<String,Object> getMembers();
	Map<String,Object> getMember(Integer id);
	Map<String,Object> addMember(MemberVO m);
	Map<String,Object> updateMember(MemberVO m);
	Map<String,Object> deleteMember(Integer id);
}
