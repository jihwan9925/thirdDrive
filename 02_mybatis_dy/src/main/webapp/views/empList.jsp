<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록 조회</title>

<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<c:set var="path" value="${pageContext.request.contextPath }"/>

</head>
<body>
	<a href="${path }">home</a>
	<h2>사원조회 결과</h2>
	<form action="${path }/searchEmp.do" method="post">
		<table>
			<tr>
				<td>
					<select name="type">
						<option value="emp_id">사원번호</option>
						<option value="emp_name">사원이름</option>
						<option value="email">이메일</option>
						<option value="phone">전화번호</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword">
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<label><input type="radio" name="gender" value="M">남</label>
					<label><input type="radio" name="gender" value="F">여</label>	
				</td>
			</tr>
			<tr>
				<td>급여</td>
				<td>
					<input type="number" name="salary" step="500000" min="1200000">
					<label><input type="radio" name="salFlag" value="ge">이상</label>
					<label><input type="radio" name="salFlag" value="le">이하</label>
				</td>
			</tr>
			<tr>
				<td>부서</td>
				<td>				
					<label><input type="checkbox" name="deptCode" value="D1">인사관리부</label>
					<label><input type="checkbox" name="deptCode" value="D2">회계관리부</label>
					<label><input type="checkbox" name="deptCode" value="D3">마케팅부</label>
					<label><input type="checkbox" name="deptCode" value="D4">국내영업부</label>
					<label><input type="checkbox" name="deptCode" value="D5">해외영업1부</label>
					<label><input type="checkbox" name="deptCode" value="D6">해외영업2부</label>
					<label><input type="checkbox" name="deptCode" value="D7">해외영업3부</label>
					<label><input type="checkbox" name="deptCode" value="D8">기술지원부</label>
					<label><input type="checkbox" name="deptCode" value="D9">총무부</label>
				</td>
			</tr>
			<tr>
				<td>직급</td>
				<td>				
					<label><input type="checkbox" name="jobCode" value="J1">대표</label>
					<label><input type="checkbox" name="jobCode" value="J2">부사장</label>
					<label><input type="checkbox" name="jobCode" value="J3">부장</label>
					<label><input type="checkbox" name="jobCode" value="J4">차장</label>
					<label><input type="checkbox" name="jobCode" value="J5">과장</label>
					<label><input type="checkbox" name="jobCode" value="J6">대리</label>
					<label><input type="checkbox" name="jobCode" value="J7">사원</label>
				</td>
			</tr>
			<tr>
				<td>입사일</td>
				<td>
					<input type="date" name="hireDate">
					<label><input type="radio" name="hireFlag" value="ge">이상</label>
					<label><input type="radio" name="hireFlag" value="le">이하</label>
				</td>
			</tr>
			
			<tr>
				<td><input type="submit" value="검색"> </td>
			</tr>
		</table>
	</form>
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
					<th>부서코드</th>
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
            		<td>${e.deftCode }</td>
            		<td>${e.jobCode }</td>
            		<td>${e.salLevel }</td>
            		<td><fmt:formatNumber value="${e.salary}" type="currency"/>원</td>
            		<td><fmt:formatNumber value="${e.bouns}" type="percent"/></td>
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