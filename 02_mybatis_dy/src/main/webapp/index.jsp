<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<c:set var="path" value="${pageContext.request.contextPath }"/>

</head>
<body>
	<h2><a href="${path }/emp/empPage.do">사원조회하기</a></h2>
	
	<h2>Data 연관관계설정하기</h2>
	<h3><a href="${path }/emp/association.do">join으로 객체 가져오기</a></h3>
	
	<h2>부서 조회하기</h2>
	<h3><a href="${path }/selectDeptAll.do">전체부서 가져오기</a></h3>
	
	<h2>다른 환경 접속하기</h2>
	<h3><a href="${path }/member.do">회원 가져오기</a></h3>
	
	<h2>게시글 가져오기</h2>
	<h3><a href="${path }/board.do?no=61">게시글&댓글 전체출력, 작성자이름, 이메일 출력</a></h3>
	
	
</body>
</html>