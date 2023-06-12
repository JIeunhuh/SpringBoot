package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

//list로 구현
public class MemberDAO1 implements MemberInterface {

	private List<MemberVO> list;

	@Override
	public List<MemberVO> getMembers() {
		list = new ArrayList<>();
		for (MemberVO m : list) {
			list.add(m);
		}
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
		m.setId(list.size() + 1);
		m.setName("name" + 1);
		m.setPass("pass" + 1);
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
