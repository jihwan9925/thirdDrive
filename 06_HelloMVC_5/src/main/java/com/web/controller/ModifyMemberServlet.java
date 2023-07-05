package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.model.dto.MemberDTO;
import com.web.model.service.MemberService;

/**
 * Servlet implementation class ModifyMemberServlet
 */
@WebServlet("/member/ModifyMember.do")
public class ModifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원정보를 수정하는 서비스
		//1. 클라이언트가 보낸 데이터 받아오기
		MemberDTO m=MemberDTO.builder()
				.userId(request.getParameter("userId"))
				.userName(request.getParameter("userName"))
				.age(Integer.parseInt(request.getParameter("age")))
				.gender(request.getParameter("gender").charAt(0))
				.email(request.getParameter("email"))
				.phone(request.getParameter("phone"))
				.address(request.getParameter("address"))
				.hobby(request.getParameterValues("hobby"))
				.build();
		
//		System.out.println(m);
		//2. 회원정보 수정(DB)
		int result=new MemberService().modifyMember(m);
//		System.out.println(result);
		//3. 결과 전송하기
		String msg="",loc="";
		
		if(result>0) {
			msg="회원정보가 수정되었습니다.";
			loc="/";
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", new MemberService().selectByUserId(m.getUserId()));
		}else {
			msg="회원정보 수정실패했습니다. 다시 시도해주세요";
//			loc="/views/member/memberView.jsp"; //이주소 그대로 쓰면 memberView.jsp에서 다른 서블릿
			//에서 불러왔던 값을 먼저불러오지 못하여 오류가 발생 
			loc="/member/memberView.do?userId="+m.getUserId();
			//memberView.jsp를 불러오는 서블릿에서 접근한다.
			//값은 쿼리스트링으로 전송
			//보내는 값이 userId하나인 이유 : 해결하려는 문제가 바뀐 이름을 해더에서 적용하는 문제여서 이름만 받는다. 
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
