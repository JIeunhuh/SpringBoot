package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

//list로 구현
public class MemberDAO1 implements MemberInterface {

	private List<MemberVO> list;

	public MemberDAO1() {
		list = new ArrayList<>();
		for (int i=1; i<=5; i++) {
			list.add(MemberVO.builder().id(i).name("name"+i).pass("pass"+i).regidate(new Date()).build());
		}
	}
	@Override
	public List<MemberVO> getMembers() {
		
		return list;
	}

	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO m) {
		int id = list.size()+1;
		m.setId(id);
		m.setName(m.getName());
		m.setPass(m.getPass());
		m.setRegidate(new Date());
		list.add(m);
		return m;
	}

	@Override
	public MemberVO updateMember(MemberVO m) {
		for (MemberVO member : list) {
			if (m.getId() == member.getId())
				member.setName(m.getName());
				member.setPass(m.getPass());
			return m;
		}
		return null;
	}

	@Override
	public int deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return id;
			}
		}
		return 0;
	}

}
