package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.model.dto.MemberDTO;
import com.web.model.service.MemberService;

/**
 * Servlet implementation class IdDuplicateServlet
 */
@WebServlet("/member/idDuplicate.do")
public class IdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdDuplicateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//클라이언트가 전송한 값(userId)이 DB(member테이블)에 있는지 확인하기
		String userId=request.getParameter("userId");
		MemberDTO m= new MemberService().selectByUserId(userId);
		
		request.setAttribute("result", m);
		request.getRequestDispatcher("/views/member/idDuplicate.jsp").forward(request, response);
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("/views/member/idDuplicate.jsp");
//		rd.forward(request, response);		

//		response.sendRedirect(request.getContextPath()+"/views/member/idDuplicate.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
