package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogH2DAOimpl implements LogInterface {
	
	@Autowired
	private DataSource datasource;

	
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

	public Object addLog(Map<String,Object> map) { //map을 가져옴
		try {
			
			PreparedStatement ps = datasource.getConnection().prepareStatement("insert into DBLOG(method,sqlstring,success) values(?,?,?)");
			ps.setObject(1, map.get("method"));
			ps.setObject(2, map.get("sqlstring"));
			ps.setObject(3, map.get("success"));
			ps.executeUpdate();
			
			return map.values();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
