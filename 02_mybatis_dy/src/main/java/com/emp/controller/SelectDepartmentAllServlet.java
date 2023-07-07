package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.dto.Department;
import com.emp.model.service.EmpService;
import com.emp.model.service.EmpServiceImpl;

/**
 * Servlet implementation class SelectDepartmentAllServlet
 */
@WebServlet("/selectDeptAll.do")
public class SelectDepartmentAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	EmpService service = new EmpServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDepartmentAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Department> depts=service.selectAllDept();
		
		request.setAttribute("depts",depts);
		
		request.getRequestDispatcher("/views/depts.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
