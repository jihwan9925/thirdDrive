package com.emp.model.service;

import static com.emp.common.SessionTemplate.getSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.emp.model.dao.EmpDao;
import com.emp.model.dao.EmpDaoImpl;
import com.emp.model.dto.Department;
import com.emp.model.dto.Employee;


public class EmpServiceImpl implements EmpService{
	private EmpDao dao = new EmpDaoImpl();
	
	@Override
	public List<Employee> selectEmployeePage(int cPage, int numPerpage){
		SqlSession session = getSession();
		List<Employee> student = dao.selectEmployeePage(session,cPage,numPerpage);
		session.close();
		return student;
	}
	
	@Override
	public int selectEmpCount() {
		SqlSession session = getSession();
		int count = dao.selectEmpCount(session);
		session.close();
		return count;
	}
	
	@Override
	public List<Employee> searchEmp(Map<String, Object> param) {
		SqlSession session = getSession();
		List<Employee> list = dao.searchEmp(session,param);
		session.close();
		return list;
	}
	
	@Override
	public List<Department> selectAllDept(){
		SqlSession session = getSession();
		List<Department> dept = dao.selectAllDept(session);
		session.close();
		return dept;
	}
}
