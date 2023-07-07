package com.emp.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.emp.model.dto.Board;

public class BoardDaoImpl implements BoardDao{

	@Override
	public List<Board> boardList(SqlSession session, int no){
		List<Board> list =session.selectList("member.boardList",no);
		list.forEach(System.out::println);
		return list; 
	};
}
