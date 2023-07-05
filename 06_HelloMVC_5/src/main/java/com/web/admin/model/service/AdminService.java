package com.web.admin.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.web.admin.model.dao.AdminDao;
import com.web.model.dto.MemberDTO;
public class AdminService {
	
	private AdminDao dao=new AdminDao();
	
	public List<MemberDTO> viewMember(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<MemberDTO> result=dao.selectMemberAll(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	public int selectMembersearchCount(Map map) {
		Connection conn=getConnection();
		int result=dao.selectMembersearchCount(conn,map);
		close(conn);
		return result;
	}
	
	public List<MemberDTO> searchMember(Map pagemap,Map map) {
		Connection conn=getConnection();
		List<MemberDTO> result=dao.searchMember(conn,pagemap,map);
		close(conn);
		return result;
	}
	
	
}






