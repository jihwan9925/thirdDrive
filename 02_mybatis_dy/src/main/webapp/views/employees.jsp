<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원목록 조회+부서테이블</title>

<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<a href="${path }">home</a>
	<h2>사원조회 결과</h2>
	<section id="board-container">
			
		<c:if test="${empty employee }">
			<table class="tbl-board">
				<tr>
	            	<td colspan="6">조회된 사원이 없습니다.</td>
	            </tr>
	        </table>
		</c:if>
		<c:if test="${not empty employee }">
			<table class="table table-striped">
				<tr>
					<th>사원번호</th>
					<th>직원명</th>
					<th>주민등록번호</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>부서</th>
					<th>직급코드</th>
					<th>급여등급</th>
					<th>급여</th>
					<th>보너스율</th>
					<th>관리자사번</th>
					<th>입사일</th>
					<th>퇴사일</th>
					<th>퇴사여부</th>
					<th>성별</th>
				</tr>
			<c:forEach var="e" items="${employee }">
				<tr>
            		<td>${e.empId }</td>
            		<td>${e.empName }</td>
            		<td>${e.empNo }</td>
            		<td>${e.email }</td>
            		<td>${e.phone }</td>
            		<td>
            			${e.dept.deptTitle }(${e.dept.deptId })
            			<ul>
            				<%-- <li>${e.dept.deptId }</li>
            				<li>${e.dept.deptTitle }</li>
            				<li>${e.dept.locationId }</li> --%>
            			</ul>
            		</td>
            		<td>${e.jobCode }</td>
            		<td>${e.salLevel }</td>
            		<td><fmt:formatNumber value="${e.salary}" type="currency"/>원</td>
            		<td><fmt:formatNumber value="${e.bonus}" type="percent"/></td>
            		<td>${e.managerId }</td>
            		<td><fmt:formatDate value="${e.hireDate}"/></td>
					<td><fmt:formatDate value="${e.entDate}"/></td>
            		<td>${e.entYn }</td>
            		<td>${e.gender=='F'?"여":"남"}</td>
				</tr>
			</c:forEach>
			</table>
		</c:if>

		<div id="pageBar">${pageBar }</div>
	</section>
	
	
</body>
</html>