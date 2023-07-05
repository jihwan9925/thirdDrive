package com.web.admin.model.dao;

import static com.web.common.JDBCTemplate.close;
import static com.web.model.dao.MemberDao.getMember;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.web.model.dto.MemberDTO;
public class AdminDao {
	
	private Properties sql=new Properties();
	
	public AdminDao() {
		String path=AdminDao.class.getResource("/sql/admin/adminsql.properties").getPath();
		try(FileReader fr=new FileReader(path);){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberDTO> selectMemberAll(Connection conn, int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDTO> result=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("viewMember"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();			
			while(rs.next()) {
				result.add(getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	public int selectMembersearchCount(Connection conn, Map map) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectMembersearchCount"));
			String sqlc=sql.getProperty("selectMembersearchCount");
			sqlc=sqlc.replace("#type", (String)map.get("type"));
			pstmt=conn.prepareStatement(sqlc);
			pstmt.setString(1, map.get("type").equals("gender")? (String)map.get("keyword"):"%"+(String)map.get("keyword")+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<MemberDTO> searchMember(Connection conn,Map pagemap, Map map){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDTO> result=new ArrayList();
		//코드를 해치지 않게 하기위해서 변수선언
		int cPage=(int)pagemap.get("cPage");
		int numPerpage=(int)pagemap.get("numPerpage");
//		System.out.println("cPage : "+cPage);
//		System.out.println("numPerpage-dao : "+numPerpage);
//		System.out.println("searchType : "+(String)map.get("type"));
//		System.out.println("searchKeyword : "+(String)map.get("keyword"));
		try {
			String sqlc=sql.getProperty("searchType");
			sqlc=sqlc.replace("#type", (String)map.get("type"));
			pstmt=conn.prepareStatement(sqlc);
//			type이 성별이면 like를 사용할 이유가 없기 때문에 퍼센트를 제거한다.(그럼 sql문도 like를 지워야하지 않나? - like사용해도 로직의 문제는 없다.)
			pstmt.setString(1, map.get("type").equals("gender")? (String)map.get("keyword"):"%"+(String)map.get("keyword")+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
}
