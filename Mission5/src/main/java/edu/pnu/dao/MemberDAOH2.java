package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;
@Repository
public class MemberDAOH2 implements MemberInterface{
	
	@Autowired
	private DataSource datasource;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDAOH2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public Map<String,Object> getMembers() {
		String sql = "select * from member";
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		List<MemberVO> list = jdbcTemplate.query(sql, new RowMapper<MemberVO>() {
		
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {

					return  MemberVO.builder()
								.id(rs.getInt("id"))
								.pass(rs.getString("pass"))
								.name(rs.getString("name"))
								.regidate(rs.getDate("regidate"))
								.build();
			}	
		});
		map.put("method","get");
		map.put("sqlstring",sql);
		map.put("success",true);
		map.put("result",list);

		return map; //map 전체를 return해서 service.java에서 map을 가져올 수 있게 함
	}

	@Override
	public Map<String,Object> getMember(Integer id) {
		Map<String,Object> map = new HashMap<>(); //map객체 생성
		MemberVO m = null;
		try (Connection con = datasource.getConnection()){
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
		try  (Connection con = datasource.getConnection()){
			
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
		try  (Connection con = datasource.getConnection()){
		
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
		try  (Connection con = datasource.getConnection()) {
			
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
