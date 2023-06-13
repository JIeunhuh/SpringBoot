package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAO02 implements MemberInterface{

	private Connection con;
	
	public MemberDAO02() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission3","sa","tiger"); 
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from member;");
			while(rs.next()) {
			MemberVO m = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				list.add(m);
			}
			rs.close();
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		MemberVO m = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(String.format("select * from member where id=%d",id));
			rs.next();
			m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			rs.close();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public MemberVO addMember(MemberVO m) {
		try {
			PreparedStatement ps = con.prepareStatement("insert into member(pass,name) values(?,?)");
			ps.setString(1, m.getPass());
			ps.setString(2, m.getName());
			ps.executeUpdate();
			
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public MemberVO updateMember(MemberVO m) {
		try {
			PreparedStatement ps = con.prepareStatement("update member set pass=?, name=? where id=?");
			ps.setString(1, m.getPass());
			ps.setString(2, m.getName());
			ps.setInt(3, m.getId());
		
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public int deleteMember(Integer id) {
			MemberVO m = null;
		try {
			PreparedStatement ps = con.prepareStatement("delete from member where id =?");
			ps.setInt(1, id);
			int ret = ps.executeUpdate();
			ps.close();
			return ret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
