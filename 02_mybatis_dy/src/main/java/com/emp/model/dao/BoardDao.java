package com.emp.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.emp.model.dto.Board;

public interface BoardDao {

	List<Board> boardList(SqlSession session, int no);
}
