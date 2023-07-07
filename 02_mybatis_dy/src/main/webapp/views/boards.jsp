<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 출력</title>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundlb.min.js"></script>
</head>
<body>
	<a href="${path }">home</a>
	<h2>게시물</h2>
	<section id="board-container">

		<c:if test="${empty boards }">
			<table class="tbl-board">
				<tr>
	            	<td colspan="6">조회된 게시물이 없습니다.</td>
	            </tr>
	        </table>
		</c:if>
		<c:if test="${not empty boards }">
			<table class="table table-striped">
				<tr>
					<th>게시글번호</th>
					<th>게시글제목</th>
					<th>게시글작성자 이름</th>
					<th>게시글 내용</th>
					<!-- <th>작성자 이름</th> -->
					<th>이메일</th>
					<th>게시글 올린날짜</th>
					<th>조회수</th>
				</tr>
			<c:forEach var="b" items="${boards }">
					<tr>
	            		<td>${b.boardNo }</td>
	            		<td>${b.boardTitle }</td>
	            		<!-- 이름을 불러오는데 member가 아닌 boardWriter를 불러오는 이유 : 
	            			 작성한 사람의 개인정보니까 member보다 boardWriter가 더 정확한 표현이 된다.
	            		 -->
	            		<td>${b.boardWriter.userName }</td><!-- 이름 -->
	            		<td>${b.boardContent }</td>
	            		<%-- <td>${b.Members}</td><!-- 이름 -->  --%>
	            		<td>${b.boardWriter.email}</td><!-- 이메일 -->
	            		<td><fmt:formatDate value="${b.boardDate}"/></td>
	            		<td>${b.boardReadCount }</td>
	            	</tr>
	            	<tr>
	            		<br><br><br><br>
	            	</tr>	
	            	
	            	<tr>
	            		<th>게시판 댓글번호</th>
						<th>게시판 댓글레벨</th>
						<th>게시판 댓글작성자</th>
						<!-- <th>작성자 이름</th>
						<th>이메일</th> -->
						<th>게시판 댓글</th>
						<th>참조원글번호</th>
						<th>게시판댓글 참조번호</th>
						<th>게시판댓글 작성일</th>
	            	</tr>
           		<c:forEach var="c" items="${b.boardComment }">
           			<tr>
	            		<td>${c.boardCommentNo }</td>
	            		<td>${c.boardCommentLevel }</td>
	            		<td>${c.boardCommentWriter }</td><!-- 이름 -->
	            		<%-- <td>${c.boardCommentWriter }</td><!-- 이름 -->
	            		<td>${c.boardCommentWriter }</td><!-- 이메일 --> --%>
	            		<td>${c.boardCommentContent }</td>
	            		<td>${c.boardRef }</td>
	            		<td>${c.boardCommentRef }</td>
	            		<td><fmt:formatDate value="${c.boardCommentDate}"/></td>            	
					</tr>
           		</c:forEach>
			</c:forEach>
			</table>
		</c:if>

		<div id="pageBar">${pageBar }</div>
	</section>
	
</body>
</html>