package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String,Object> getMembers() {
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		List<MemberVO> list = new ArrayList<>();
		try {
			String sql = "select * from member";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
			MemberVO m = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				list.add(m);
				map.put("method","get"); //method == get
				map.put("sqlstring", sql);//sqlstring == sql문
				map.put("success",true);//success 여부 
				map.put("result", list);
			}
			rs.close();
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
			map.put("success", false); //실행하지 못하면 false
		}
		return map; //map 전체를 return해서 service.java에서 map을 가져올 수 있게 함
	}

	@Override
	public Map<String,Object> getMember(Integer id) {
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		MemberVO m = null;
		try {
			String sql = "select * from member where id=%d";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(String.format(sql,id));
			rs.next();
			m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			map.put("method","get"); //method == get
			map.put("sqlstring", sql);//sqlstring == sql문
			map.put("success",true);//success 여부 
			map.put("result", m);
			
			rs.close();
			st.close();
			
			return map;
			
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false); //실행하지 못하면 false
		}
		return null;
	}

	@Override
	public Map<String,Object> addMember(MemberVO m) {
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		try {
			
			String sql ="insert into member(pass,name) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, m.getPass());
			ps.setString(2, m.getName());
			ps.executeUpdate();
			
			map.put("method","post"); //method == get
			map.put("sqlstring", sql);//sqlstring == sql문
			map.put("success",true);//success 여부 
			map.put("result", m);
			ps.close();
			
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return null;
	}

	@Override
	public Map<String,Object> updateMember(MemberVO m) {
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		try {
		
			String sql = "update member set pass=?, name=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, m.getPass());
			ps.setString(2, m.getName());
			ps.setInt(3, m.getId());
			ps.executeUpdate();
			
			map.put("method","put"); //method == put
			map.put("sqlstring", sql);//sqlstring == sql문
			map.put("success",true);//success 여부 
			map.put("result", m);
		
			ps.close();
			
			return map;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			map.put("success",false);
		}
		return null;
	}

	@Override
	public Map<String,Object> deleteMember(Integer id) {
		//	MemberVO m = null;
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		try {
			
			String sql = "delete from member where id =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int ret = ps.executeUpdate();
		
			map.put("method","delete"); //method == delete
			map.put("sqlstring", sql);//sqlstring == sql문
			map.put("success",true);//success 여부 
			map.put("result", ret);
			
			ps.close();
			return map;
			
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("success",false);
		}
		return null;
	}

}
