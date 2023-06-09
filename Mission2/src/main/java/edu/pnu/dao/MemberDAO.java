package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDAO {

	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/Mission2";
	private String userid = "scott";
	private String userpw = "tiger";

	private Connection con;

	public MemberDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, userpw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member getMember(Long id) { //member data 하나씩 보기
		Member m = null;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(String.format("select * from Member where id = %d", id));

			rs.next();

			m = Member.builder()
					.id(rs.getLong("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();

			rs.close();
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return m;
	}

	public List<Member> getMembers() { //select all
		List<Member> list = new ArrayList<>();
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(String.format("select * from Member"));

			while (rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
			list.add(m);
			}
			

			rs.close();
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}

	public Member insertMember(Member m) { //insert into

		try {
			PreparedStatement ps = con.prepareStatement("insert into member(pass,name) values(?,?);");
			ps.setString(1, m.getPass());
			ps.setString(2, m.getName());
			ps.executeUpdate();

			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	public Member updateMember(Member m) { //update
		try {
			PreparedStatement ps = con.prepareStatement("update member set pass=?, name=? where id=?");
			ps.setString(1, m.getPass());
			ps.setString(2, m.getName());
			ps.setLong(3, m.getId());
			ps.executeUpdate();
			
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public int deleteMember(Long id) { //id 입력해서 data delete

		try {
			PreparedStatement ps = con.prepareStatement("delete from member where id=?");
			ps.setLong(1, id);
			int ret = ps.executeUpdate();
			ps.close();
			return ret;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
