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
 * Servlet implementation class ModifyPasswordServlet
 */
@WebServlet("/updatePasswordEnd")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String oriPw=request.getParameter("password");
		String newPw=request.getParameter("password_new");
		System.out.println(userId+" "+oriPw+" "+newPw);
		MemberDTO m=new MemberService().selectByUserIdAndPw(userId,oriPw);
		String msg="",loc="/member/updatePassword.do?userId="+userId;
		//쿼리 셀렉터로 값을 넣는이유 : 한번 변경후에 값이 없으면 어떤아이디의 비번을 변경하는지 알수가 없다.
		if(m==null) {
			//비번 일치하지 않음
			msg="비밀번호가 일치하지않습니다.";
			loc="/member/updatePassword.do";
		}else {
			//비번 일치함
			int result=new MemberService().updatePassword(userId,newPw);
			if(result>0) {
				msg="비밀번호가 수정완료.";
				loc="/";
				request.setAttribute("script", "opener.location.replace('"+request.getContextPath()+"/logout.do');close();");
			}else {
				msg="비밀번호가 수정실패.";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp")
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
