<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 출력</title>

<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<c:if test="${not empty depts }">
		<c:forEach var="d" items="${depts }">
			<ul>
				<li>${d.deptId } 인원수 : ${d.employee.size() }</li>
				<li>${d.deptTitle }</li>
				<li>
					<c:if test="${not empty d.employee }">
						<c:forEach var="e" items="${d.employee }">
							<table>
								<tr>
									<td>${e.empName }</td>
									<td>${e.jobCode }</td>
									<td>${e.salary }</td>
								</tr>
							</table>
						</c:forEach>
					</c:if>
				</li>
				<li>${d.locationId}</li>
			</ul>
		</c:forEach>
	</c:if>
</body>
</html>