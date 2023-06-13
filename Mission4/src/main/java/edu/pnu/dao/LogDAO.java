package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

import edu.pnu.domain.LogVO;

public class LogDAO {
	private Connection con = null;

	public LogDAO() {

		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission3", "sa", "tiger");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public LogVO addLog() {
//		try {
//			
//			PreparedStatement ps = con.prepareStatement("insert into DBLOG(method,sqlstring,success) values(?,?,?)");
//			ps.setString(1, l.getMethod());
//			ps.setString(2, l.getSqlstring());
//			ps.setBoolean(3, l.isSuccess());
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public LogVO addLog(Map<String,Object> map) { //map을 가져옴
		try {
			
			PreparedStatement ps = con.prepareStatement("insert into DBLOG(method,sqlstring,success) values(?,?,?)");
			ps.setObject(1, map.get("method"));
			ps.setObject(2, map.get("sqlstring"));
			ps.setObject(3, map.get("success"));
			ps.executeUpdate();
			
			return (LogVO) map;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
