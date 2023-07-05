package com.web.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.web.common.JDBCTemplate;
import com.web.model.dto.MemberDTO;

public class MemberDao {

	private final Properties sql=new Properties();//final => 성능up
	
	{ //
		String path=MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO selectByUserIdAndPw(Connection conn,
			String userId, String password) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByUserIdAndPw"));
			pstmt.setString(1,userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}return m;
	}

	
	public MemberDTO login(SqlSession session,Map login){
		System.out.println(login.get("userId"));
		return session.selectOne("login.selectByUserIdAndPw",login);
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		MemberDTO list=null;//check!!
//		try {
//			pstmt = conn.prepareStatement(sql.getProperty("selectByUserIdAndPw"));//selectByUserIdAndPw=SELECT * FROM MEMBER WHERE USERID=? AND PASSWORD=?
//			//메소드명, 키값, 주소는 복사하기!
//			pstmt.setString(1,userId);//userId는 DB에서 PK(유일값) -> row는 1개! 따라서 반환값이 MemberDTO여도 되는 것이구나!
//			pstmt.setString(2,password);
//			rs = pstmt.executeQuery();//executeQuery : select문이다!
//			while(rs.next()) {
//				list=getMember(rs);
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(pstmt);
//		}
//		return list;
	}
	
	public int insertMember(Connection conn, MemberDTO m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertMember"));//INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, String.valueOf(m.getGender()));//v 
			pstmt.setInt(5, m.getAge());//v
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7,m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",", m.getHobby()));//v
			
			result=pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public MemberDTO selectByUserId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByUserId"));//SELECT * FROM MEMBER WHERE USERID=?
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}return m;
		
	}
	
	
	public static MemberDTO getMember(ResultSet rs) throws SQLException{
		return MemberDTO.builder()
				.userId(rs.getString("userid"))
				.password(rs.getString("password"))
				.userName(rs.getString("username"))
				.gender(rs.getString("gender").charAt(0))
				.age(rs.getInt("age"))
				.email(rs.getString("email"))
				.phone(rs.getString("phone"))
				.address(rs.getString("address"))
				.hobby(rs.getString("hobby")!=null?rs.getString("hobby").split(","):null)
				.enrollDate(rs.getDate("enrolldate"))
				.build();
//		gender, hobby, enrolldate 방식 체크!
//				list.add(m);// list아니니까 필요없음!
				
	}
	
	public int modifyMember(Connection conn, MemberDTO m) {
		PreparedStatement pstmt=null;
		String sql2=sql.getProperty("modifyMember");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("modifyMember"));
			pstmt.setString(1, m.getUserName());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, String.valueOf(m.getGender()));
			pstmt.setString(4, m.getEmail());//v 
			pstmt.setString(5, m.getPhone());//v
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7,String.join(",", m.getHobby()));
			pstmt.setString(8, m.getUserId());
			System.out.println("sql결과 : "+m.getUserName());
			result=pstmt.executeUpdate();
			System.out.println("result : "+result);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updatePassword(Connection conn, String userId, String password) {
		PreparedStatement pstmt=null;
		String sql2=sql.getProperty("modifyMember");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updatePassword"));
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
}
