package com.emp.model.service;

import static com.emp.common.SessionTemplate.getWebSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.emp.model.dao.BoardDao;
import com.emp.model.dao.BoardDaoImpl;
import com.emp.model.dto.Board;

public class BoardServiceImpl implements BoardService{
private BoardDao dao = new BoardDaoImpl();
	
	@Override
	public List<Board> boardList(int no){
		SqlSession session = getWebSession();
		List<Board> board = dao.boardList(session,no);
		session.close();
		return board;
	}
}
