package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.dto.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/insertComment.do")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardComment bc=BoardComment.builder()
				.boardRef(Integer.parseInt(request.getParameter("boardRef")))
				.level(Integer.parseInt(request.getParameter("level")))
				.boardCommentWriter(request.getParameter("boardCommentWriter"))
				.boardCommentContent(request.getParameter("content"))
				.boardCommentRef(Integer.parseInt(request.getParameter("boardCommentRef")))
				.build();
		
		int result=new BoardService().insertBoardComment(bc);
		String view;
		if(result>0) {
			//등록가능->등록값을 전송
			view=request.getContextPath()+"/board/boardView.do?no="+bc.getBoardRef();
			//기존에 남아있는 insertComment값이 남아있기때문에 버리기위해 sendRedirect
			response.sendRedirect(view);
		}else {
			//등록불가능->등록실패결과출력후 boardView로 이동
			request.setAttribute("msg", "댓글등록실패");
			request.setAttribute("loc", "/board/boardView.do?no="+bc.getBoardRef());
			view="/views/common/msg.jsp";
			request.getRequestDispatcher(view).forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
