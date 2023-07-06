package com.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.dto.Employee;
import com.emp.model.service.EmpService;
import com.emp.model.service.EmpServiceImpl;

/**
 * Servlet implementation class SearchEmpServlet
 */
@WebServlet("/searchEmp.do")
public class SearchEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService service = new EmpServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		/*
		 * String type = request.getParameter("type"); String keyword =
		 * request.getParameter("keyword"); String gender =
		 * request.getParameter("gender"); int salary =
		 * Integer.parseInt(request.getParameter("salary")); String salFlag =
		 * request.getParameter("salFlag");
		 */
		
		Map<String, Object> param=new HashMap<>();
		param.put("type", request.getParameter("type"));
		param.put("keyword", request.getParameter("keyword"));
		param.put("gender",request.getParameter("gender"));
		//상함연산자로 0을 넣는 이유 : 숫자로 변환하는 과정에서 오류가 나기 때문에 아무값이나 넣어서 변환을 진행시키면 된다.
		param.put("salary",Integer.parseInt(request.getParameter("salary").equals("")?"0":request.getParameter("salary")));
		param.put("salFlag",request.getParameter("salFlag"));
		param.put("deptCodes",request.getParameterValues("deptCode"));
		param.put("jobCodes",request.getParameterValues("jobCode"));
		param.put("hireDate",(String)request.getParameter("hireDate"));
		param.put("hireFlag",request.getParameter("hireFlag"));
		/*  Map.of 를 사용하지 않는 이유 : Map.of는 null값을 사용할 수 없다.
		 * Map param = Map.of("type",type,"keyword",keyword,"gender",gender);
		 */
		List<Employee> searchEmp = service.searchEmp(param);
		
		request.setAttribute("employee", searchEmp);
		request.getRequestDispatcher("/views/empList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
