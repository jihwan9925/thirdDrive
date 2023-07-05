package com.web.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentUpdateServlet
 */
@WebServlet("/board/updateComment.do")
public class BoardCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 내용, 수정댓글번호, 
		String content = request.getParameter("content");
		int commentNo = Integer.parseInt(request.getParameter("boardCommnetNo"));
		System.out.println("no : "+commentNo+" content : "+content);
		int result=new BoardService().BoardCommentUpdate(content , commentNo);

		String msg,loc;
		if(result>0) {
			msg = "정상적으로 수정되었습니다.";
		}else {
			msg = "수정 실패";
		}
		int no=Integer.parseInt(request.getParameter("no"));
		/* String loc = "/board/boardView.do?no=" */
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/board/boardView.do?no="+no);
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
