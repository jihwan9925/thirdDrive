<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.web.board.model.vo.dto.Board" %>
<%
	//서블릿에 있음
	List<Board> boards=(List)request.getAttribute("boards");
%>

<%@ include file="/views/common/header.jsp"%>
<style>
	section#board-container{width:600px; margin:0 auto; text-align:center;}
	section#board-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
	div#pageBar span.cPage{color: #0066ff;}
</style>

	<section id="board-container">
		<h2>게시판 </h2>
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<%if(boards.isEmpty()){ %>
            <tr>
            	<td colspan="6">조회된 공지사항이 없습니다.</td>
            </tr>
            <%}else{ 
            	for(Board b : boards){%>
            	<tr>
            		<td><%=b.getBoardNo() %></td> <!-- 번호 -->
            		<td><a href="<%=request.getContextPath()%>/board/boardView.do?no=<%=b.getBoardNo()%>">
            		<%=b.getBoardTitle() %></a></td><!-- 제목 -->
            		<td><%=b.getBoardWriter() %></td> <!-- 작성자 -->
            		<td><%=b.getBoardDate() %></td> <!-- 작성일 -->
            		<td> <!-- 첨부파일 -->
            			<%if(b.getBoardOriginal()!=null) {%>
            				<img alt="" src="<%=request.getContextPath() %>/images/file.png" width="20">
            			<%} %>
            		</td>
            		<td><%=b.getBoardReadCount() %></td> <!-- 조회수 -->
				</tr>
		    <%}
		    }%>	
		</table>

		<div id="pageBar"><%=request.getAttribute("pageBar") %></div>
	</section>

<%@ include file="/views/common/footer.jsp"%>
