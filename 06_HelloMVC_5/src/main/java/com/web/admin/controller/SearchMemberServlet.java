package com.web.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.model.dto.MemberDTO;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/admin/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터를 기준으로 member테이블에서 해당하는 데이터를 보내줌(입력타입과 입력값을 이용해서 회원필터기능을 만드는 로직)
    	//header 관리자상태로 회원조회누르면 여기로와서 입력타입,입력값가지고 리스트를 멤버로 저장 저장한걸 기존 서블릿으로 이동
    	//1. 입력타입과 입력값을 받아오기
    	String searchType=request.getParameter("searchType");
		String searchKeyword=request.getParameter("searchKeyword");
		//searchType에 따른 searchKeyword값을 map방식으로 저장
		Map map=Map.of("type",searchType,"keyword",searchKeyword);
		
		//DB의 member테이블에 저장된 전체 회원을 가져와 화면에 출력해주는 기능
		//페이징처리하기
		int cPage; //현재페이지
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerpage; //페이지당 출력될 데이터 수
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=10;
		}
		System.out.println(numPerpage);
		//cPage에 따른 numPerpage값을 map방식으로 저장
		Map pagemap=Map.of("cPage",cPage,"numPerpage",numPerpage);
		//1. DB에서 member테이블에 있는 데이터 가져오기
		List<MemberDTO> members=new AdminService().searchMember(pagemap,map);
		//2. DB에서 가져온 데이터 저장(화면출력)
		request.setAttribute("members", members);
		//3. 페이지바를 구성
		// 1) DB에 저장된 테이블에서 조건에 해당하는 데이터의 수를 가져오기
		int totalData=new AdminService().selectMembersearchCount(map);
//		System.out.println("totalData : "+totalData);
		// 2) 전체페이지수를 계산하기 * 소수점 주의!
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		// 3) 페이지바 시작번호 계산하기
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		//4. 페이지바를 구성하는 html저장하기
		String pageBar="";
		//이전표시하기
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()
			+"?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(cPage-1)+"&numPerpage="+numPerpage+"'>[이전]</a>";
			//"&cPage="+pageNo 에서 (cPage-1)라는데 이유를 모름 : 조건자체가 이전을 누르는 상황에서 전페이지로 넘어가야해서 1 적어야한다.
		}
		//선택할 페이지 번호 출력하기
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getRequestURI()
				+"?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+"</a>";
			}pageNo++;
		}
		//다음출력
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()
			+"?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'>[다음]</a>";
		}
		request.setAttribute("pageBar", pageBar);
		//3. 출력할 화면을 선택(이동)
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
