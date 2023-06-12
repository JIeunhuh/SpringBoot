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

	private Connection con;

	// Connection 설정
	public MemberDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member insertMember(Member m) {
		try {
			PreparedStatement ps = con.prepareStatement("insert into member(name,age,nickname) values(?,?,?)");
			ps.setString(1, m.getName());
			ps.setInt(2, m.getAge());
			ps.setString(3, m.getNickname());
			ps.executeUpdate();
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	public List<Member> getMembers() {
		List<Member> list = new ArrayList<>();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from member");
			while (rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname")).build();

				list.add(m);
			}
		
			rs.close();
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Member getMember(Long id) {
		Member m = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(String.format("select * from where id = %d ",id));
			rs.next();
			m = Member.builder()
					.id(rs.getLong("id"))
					.name(rs.getString("name"))
					.age(rs.getInt("age"))
					.nickname(rs.getString("nickname")).build();

			
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
}
