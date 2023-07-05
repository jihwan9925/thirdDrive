package com.web.board.model.vo.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.board.model.vo.dto.Board;
import com.web.board.model.vo.dto.BoardComment;

public class BoardDao {
private Properties sql=new Properties();
	
	public BoardDao() {
		String path=BoardDao.class.getResource("/sql/board/board_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Board getBoard(ResultSet rs) throws SQLException{
		return Board.builder()
				.boardNo(rs.getInt("board_no"))
				.boardTitle(rs.getString("board_title"))
				.boardWriter(rs.getString("board_writer"))
				.boardContent(rs.getString("board_content"))
				.boardOriginal(rs.getString("board_original_filename"))
				.boardRenamed(rs.getString("board_renamed_filename"))
				.boardDate(rs.getDate("board_date"))
				.boardReadCount(rs.getInt("board_readcount"))
				.build();
	}
	
	
	public List<Board> selectBoard(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoard"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("RN"); //테이블에서 별칭을 저장한 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public Board selectBoardByNo(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardByNo"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			if(rs.next()) b=getBoard(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
	}
	
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBoard"));
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginal());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateBoardReadCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateBoardReadCount"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBoardComment"));
			pstmt.setInt(1, bc.getLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef())); //설명 : boardView.jsp 
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public List<BoardComment> selectBoardComment(Connection conn, int boardNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardComment"));
			pstmt.setInt(1,boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoardComment(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public int BoardCommentDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("BoardCommentDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int BoardCommentUpdate(Connection conn, String content , int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("BoardCommentUpdate"));
			pstmt.setString(1, content);
			pstmt.setInt(2, commentNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	private BoardComment getBoardComment (ResultSet rs)throws SQLException{
		return BoardComment.builder()
				.boardCommentNo(rs.getInt("board_comment_no"))
				.level(rs.getInt("board_comment_level"))
				.boardCommentWriter(rs.getString("board_comment_writer"))
				.boardCommentContent(rs.getString("board_comment_content"))
				.boardCommentDate(rs.getDate("board_comment_date"))
				.boardCommentRef(rs.getInt("board_comment_ref"))
				.boardRef(rs.getInt("board_ref"))
				.build();
	}
}
