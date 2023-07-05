package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/BoardCommentDelete.do")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글의 번호 받아오기
		int no = Integer.parseInt(request.getParameter("no"));
		//게시물 번호 받아오기
		int ref = Integer.parseInt(request.getParameter("ref"));
		//삭제메소드로 보냄
		int result = new BoardService().BoardCommentDelete(no);
		//확인되면 다시 돌아감(boardView)
		String msg,loc;
		if(result>0) {
			msg = "정상적으로 삭제되었습니다.";
			loc = "/board/boardView.do?no="+ref;
		}else {
			//등록불가능->등록실패결과출력후 boardView로 이동
			msg = "댓글 삭제실패";
			loc = "/board/boardView.do?no="+ref;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
