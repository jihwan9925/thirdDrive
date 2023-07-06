<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 조회 결과</title>
</head>
<body>
	<h2>학생정보</h2>
	<c:if test="${count == null }">
		<h2>조회된 학생수가 없습니다.</h2>
	</c:if>
	<c:if test="${count != null }">
		<h3>전체학생 수 : <c:out value="${count }"/></h3>
	</c:if>
	
	<c:if test="${students.size()>0 || list.size()>0}">
		<table>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>전번</th>
				<th>이메일</th>
				<th>주소</th>
				<th>등록일</th>
			</tr>
		<c:forEach items="${students }" var="s">
			<tr>
				<td><c:out value="${s.studentNo }"/></td>
				<td><c:out value="${s.studentName }"/></td>
				<td><c:out value="${s.studentTel }"/></td>
				<td><c:out value="${s.studentEmail }"/></td>
				<td><c:out value="${s.studentAddress }"/></td>
				<td><c:out value="${s.reg_date }"/></td>
				
			</tr>				
		</c:forEach>
		<c:forEach var="s" items="${list }">
			<tr>
				<td><c:out value="${s.STUDENT_NO }"/></td>
				<td><c:out value="${s.STUDENT_NAME }"/></td>
				<td><c:out value="${s.STUDENT_TEL }"/></td>
				<td><c:out value="${s.STUDENT_EMAIL }"/></td>
				<td><c:out value="${s.STUDENT_ADDR }"/></td>
				<td><c:out value="${s.REG_DATE }"/></td>
			</tr>
		</c:forEach>	
		</table>
	</c:if>
	<h3>vo를 생성하지 않고 데이터 가져오기</h3>
	<li><c:out value="${s.STUDENT_NO }"/></li>
	<li><c:out value="${s.STUDENT_NAME }"/></li>
	<li><c:out value="${s.STUDENT_TEL }"/></li>
	<li><c:out value="${s.STUDENT_EMAIL }"/></il>
	<li><c:out value="${s.STUDENT_ADDR }"/></li>
	<li><c:out value="${s.REG_DATE }"/></li>

	
	
	
	
	
	
	
</body>
</html>