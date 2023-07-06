<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이징처리</title>

<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
	<h2>학생조회 결과</h2>
	<section id="board-container">
			
		<c:if test="${empty students }">
			<table class="tbl-board">
				<tr>
	            	<td colspan="6">조회된 회원이 없습니다.</td>
	            </tr>
	        </table>
		</c:if>
		<c:if test="${not empty students }">
			<table class="tbl-board">
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>주소</th>
					<th>등록일</th>
				</tr>
			<c:forEach var="s" items="${students }">
				<tr>
            		<td>${s.studentNo }</td>
            		<td>${s.studentName }</td>
            		<td>${s.studentTel }</td>
            		<td>${s.studentEmail }</td>
            		<td>${s.studentAddress }</td>
					<td><fmt:formatDate value="${s.reg_date}"/></td>
				</tr>
			</c:forEach>
			</table>
		</c:if>

		<div id="pageBar">${pageBar }</div>
	</section>
</body>
</html>