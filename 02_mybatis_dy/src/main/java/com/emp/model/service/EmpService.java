package com.emp.model.service;

import java.util.List;
import java.util.Map;

import com.emp.model.dto.Employee;

public interface EmpService {

	List<Employee> selectEmployeePage(int cPage,int numPerpage);
	
	int selectEmpCount();
	
	List<Employee> searchEmp(Map<String,Object> param);
}
