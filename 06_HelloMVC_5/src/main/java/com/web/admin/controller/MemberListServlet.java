package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.dao.AdminDao;
import com.web.admin.model.service.AdminService;
import com.web.common.AESEncryptor;
import com.web.model.dto.MemberDTO;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB의 member테이블에 저장된 전체 회원을 가져와 화면에 출력하는 기능
		
		//페이징처리하기
		//현재 페이지 (처음엔 1로, 다음에 cPage를 초기화하면 (어디에서?)그값으로 불려오기)
		int cPage;
		try{
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			//불려온 값이 없으면 1로 저장해서 첫번째페이지로 출력하도록 구현
			cPage=1;
		}
		//한페이지당 데이터를 출력할 갯수
		int numPerpage;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=10;
		}
		
//		String userId=request.getParameter("userId");
		//1. DB에서 member테이블에 있는 데이터 가져오기
		List<MemberDTO> members=new AdminService().viewMember(cPage,numPerpage);
		
		//2. 페이지바를 구현
		//   *DB에 저장된 전체 데이터 수 가져오기(sql에서 COUNT 사용)
		int totalData=new AdminService().selectMemberCount();
		//   *전체 페이지수 계산하기 (소수점 주의,소수점이 나온다=[1.2. . . ]처럼 한페이지를 채우지 못하는 경우를 고려)
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5; //[1.2.3.4.5]처럼 여러페이지를 얼마나 한번에 보여주는지를 뜻하는 숫자
		//   *페이지바 시작번호(1,6,11...) 계산하기
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		//   *페이지바 끝번호(5,10,15...) 계산하기
		int pageEnd=pageNo+pageBarSize-1;
		
		//3. 페이지바를 구성하는 html 저장하기(=하단 1,2,3,4,5 를 html의 텍스트로 처리)
		String pageBar="";
		if(pageNo==1) { //첫번쨰 페이지면 이전눌러도 이동이 없게 만들기위한 분기
			pageBar+="<span>[이전]</span>";
		}else {//request.getRequestURI()=/admin/memberList.do (=[6,7,8,9,10]=>[1,2,3,4,5]로 이동)
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		//   *선택할 페이지 번호출력하기
		//
		while(!(pageNo>pageEnd)||(pageNo>totalPage)) {
			if(pageNo==cPage) { //현재페이지는 선택할 수 없도록 제한
				pageBar+="<span>"+pageNo+"</span>";
			}else {  // 페이지 시작번호에 링크붙이고 +1, 
					 //페이지시작번호가 전체 페이지 수보다 크거나, 페이지바 끝번호보다 크면 중지
				pageBar+="<a href='"+request.getRequestURI()+"?cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		//   *다음출력
		if(pageNo>totalPage) {
			pageBar="<span>[다음]<span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+pageNo+"'>[다음]</a>";
		}
		//4. DB에서 가져온 데이터 저장
		request.setAttribute("members", members);
		request.setAttribute("pageBar", pageBar);
		//5. 정보를 보내기
		request.getRequestDispatcher("/views/admin/memberManage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
