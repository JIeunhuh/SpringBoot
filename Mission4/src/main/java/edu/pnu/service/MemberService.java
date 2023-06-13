package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAO02;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberInterface memberDao;
	private LogDAO logDao;

	public MemberService() {
		// memberDao = new MemberDAO1();
		memberDao = new MemberDAO02();
		logDao = new LogDAO();
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		logDao.addLog(map);
		return (MemberVO) map.get("result");

	}

	public List<MemberVO> getMembers() {

		Map<String, Object> map = memberDao.getMembers();
		logDao.addLog(map);
		return (List<MemberVO>) map.get("result");
	}

	public MemberVO addMember(MemberVO m) {
		Map<String, Object> map = memberDao.addMember(m);
		logDao.addLog(map);
		return (MemberVO) map.get("result");
	}

	public MemberVO updateMember(MemberVO m) {
		Map<String, Object> map = memberDao.updateMember(m) ;
		logDao.addLog(map);
		return (MemberVO) map.get("result");
	}

	public int deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		logDao.addLog(map);
		return (int) map.get("result");
	}
}
