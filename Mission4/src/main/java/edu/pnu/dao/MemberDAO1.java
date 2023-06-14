package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

//list로 구현
public class MemberDAO1 implements MemberInterface {

	private List<MemberVO> list;
	Map<String, Object> map;

	public MemberDAO1() {
		map = new HashMap<String, Object>();
		list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			list.add(MemberVO.builder().id(i).name("name" + i).pass("pass" + i).regidate(new Date()).build());
			map.put("result", list);
		}
	}

	@Override
	public Map<String, Object> getMembers() {

		map.put("method", "get");
		map.put("sqlstring", "");
		map.put("result", list);
		map.put("success", true);
		return map;
	}

	public Map<String, Object> getMember(Integer id) {
		map = new HashMap<>();
		for (MemberVO m : list) {
			if (m.getId() == id)
				map.put("method", "get");
			map.put("sqlstring", "");
			map.put("result", m);
			map.put("success", true);

			return map;
		}
		map.put("success", false);
		return null;
	}

	@Override
	public Map<String, Object> addMember(MemberVO m) {
		map = new HashMap<>();
		int id = list.size() + 1;
		m.setId(id);
		m.setName(m.getName());
		m.setPass(m.getPass());
		m.setRegidate(new Date());
		list.add(m);
		map.put("method","post");
		map.put("sqlstring", "");
		map.put("result", list);
		map.put("success", true);
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO m) {
		map = new HashMap<>();
		for (MemberVO member : list) {
			if (m.getId() == member.getId())
				member.setName(m.getName());
			member.setPass(m.getPass());
			map.put("method", "put");
			map.put("sqlstring", "");
			map.put("result", member);
			map.put("success", true);
			
			return map;
		}
		map.put("success", false);
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		map = new HashMap<>();
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				map.put("method", "delete");
				map.put("sqlstring", "");
				map.put("result", list);
				map.put("success", true);
				return map;
			}
		}
		map.put("success", false);
		return null;
	}

}
