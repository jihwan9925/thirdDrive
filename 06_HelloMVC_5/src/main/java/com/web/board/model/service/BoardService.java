package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.vo.dao.BoardDao;
import com.web.board.model.vo.dto.Board;
import com.web.board.model.vo.dto.BoardComment;


public class BoardService {
	private BoardDao dao=new BoardDao();
	
	public List<Board> selectBoard(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Board> list=dao.selectBoard(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	public int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectBoardCount(conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public Board selectBoardByNo(int no,boolean isRead){
		Connection conn=getConnection();
		//번호로 게시물 상세내용 불러오기
		Board b=dao.selectBoardByNo(conn, no);
		if(b!=null&&!isRead) { //isRead가 false면 값이 없는 것이니 들어간 적이 없다는 의미이고, 그때는 증가 메소드 실행||isRead가 true면 방문한 적 있기 때문에 실행을 막음
			int result=dao.updateBoardReadCount(conn,no);
			if(result>0) {
				commit(conn);
				b.setBoardReadCount(b.getBoardReadCount()+1);
			}
			else rollback(conn);			
		}
		close(conn);
		return b;
	}
	
	public int updateBoard(Board b) {
		return 0;
	}
	public int deleteBoard(Board b) {
		return 0;
	}
	
	
	public int insertBoardComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn,bc);
		if(result>0) commit(conn);
		else rollback(conn);			
		close(conn);
		return result;
	}
	
	public List<BoardComment> selectBoardComment(int boardNo) {
		Connection conn=getConnection();
		List<BoardComment> list=dao.selectBoardComment(conn,boardNo);			
		close(conn);
		return list;
	}
	
	public int BoardCommentDelete(int no) {
		Connection conn=getConnection();
		int result=dao.BoardCommentDelete(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int BoardCommentUpdate(String content , int commentNo) {
		Connection conn=getConnection();
		int result=dao.BoardCommentUpdate(conn,content,commentNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
