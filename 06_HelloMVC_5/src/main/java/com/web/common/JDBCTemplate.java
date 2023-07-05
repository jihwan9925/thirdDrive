package com.web.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	public static Connection getConnection() {
		Connection conn=null;
		Properties info=new Properties();//properties파일 이용!(보안용), source file로 properties만들어야함.. : 해결! - 일반 파일의 확장명을 properties를 만들어야함!
		try {
			String path=JDBCTemplate.class.getResource("/driver.properties").getPath();
//					/는 어디를 지칭하는지 체크해보기!
			info.load(new FileReader(path));
			Class.forName(info.getProperty("driver"));
			conn=DriverManager.getConnection(info.getProperty("url"),info.getProperty("user"),info.getProperty("pw"));
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","web","WEB");
			conn.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return conn;
	}
	
//	public static void close(Object obj) {
//		if(obj instanceof Connection c)
////		다른 방식이라고 하는데 정확히 어떤 방식인지 인지 못해냄
//	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.close();
		}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	

	public static void close(Statement stmt) {
		try {
			if(stmt!=null&&!stmt.isClosed()) 
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null&&!rs.isClosed()) 
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null&conn.isClosed())conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn!=null&conn.isClosed())conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
