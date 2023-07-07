package com.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.emp.model.dto.Department;
import com.emp.model.dto.Employee;

public interface EmpDao {

	List<Employee> selectEmployeePage(SqlSession session, int cPage,int numPerpage);
	
	int selectEmpCount(SqlSession session);
	
	List<Employee> searchEmp(SqlSession session, Map<String,Object> param);
	
	List<Department> selectAllDept(SqlSession session);
 }
