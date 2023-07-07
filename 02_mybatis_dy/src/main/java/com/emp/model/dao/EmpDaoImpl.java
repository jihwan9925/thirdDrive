package com.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.emp.model.dto.Department;
import com.emp.model.dto.Employee;


public class EmpDaoImpl implements EmpDao{
	
	@Override
	public List<Employee> selectEmployeePage(SqlSession session, int cPage, int numPerpage) {
		//selectOne()메소드를 이용해서 데이터를 조회할 수 있다.
		RowBounds rb = new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("emp.selectEmployeePage",null,rb);
	}
	@Override
	public int selectEmpCount(SqlSession session) {
		//selectOne()메소드를 이용해서 데이터를 조회할 수 있다.
		return session.selectOne("emp.selectEmpCount");
	}
	
	@Override
	public List<Employee> searchEmp(SqlSession session, Map<String, Object> param) {
		return session.selectList("emp.searchEmp",param);
	}
	
	@Override
	public List<Department> selectAllDept(SqlSession session) {
		return session.selectList("emp.selectAllDept");
	}

}
