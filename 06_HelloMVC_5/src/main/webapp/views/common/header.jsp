<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.model.dto.MemberDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	/* (*LoginfrmServlet)(로그인 기능)client-DB 유효성 검사 */ //login
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	
	/* (*LoginfrmServlet)(로그인 기능)아이디 저장 */ /* //saveId */
	Cookie[] cookies=request.getCookies(); /* 쿠키의 값은 복수형이 될 수 있기 때문에 배열로 받는다 */
	String saveId=null; /* 지역변수는 el표현식을 이용할 수 없다 */
	if(cookies!=null){ /* 쿠키안에 값이 있으면 = 로그인저장을 눌렸으면 입력한 아이디값을 쿠키에저장 */
		for(Cookie c : cookies) {
			if(c.getName().equals("saveId")) {/* 쿠키에 저장된 로그인 저장을 원하는 입력값이  */
				saveId=c.getValue();
				break;
			}
		}
	}
%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC</title>
<link rel="stylesheet" type="text/css" href="${path }/css/style.css">
<script src="${path }/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<h1><a href="${path }">HelloMVC</a></h1>
			
			<div class="login-container">
			<c:if test="${loginMember == null}">
				<form id="loginFrm" action="${path }/loginfrm.do" method="post"
				 onsubmit="return fn_validation();">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" 
								placeholder="아이디 입력" value="<%=saveId!=null?saveId:""%>">
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="패스워드 입력">
							</td>
							<td>
								<input type="submit" value="로그인">
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId" <%=saveId!=null?"checked":"" %> >
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입" onclick="location.assign('${path}/member/enrollMember.do')">
								<!--  assign 은 다음 페이지로 이동하면서 현재 페이지를 히스토리에 남기고 replace 는 남기지 않습니다.
   									  이는 브라우저의 뒤로가기를 눌렀을 때 그 차이를 확실히 알 수 있습니다. -->
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			<c:if test="${loginMember!=null}" >
				<table id="logged-in">
						<tr>
							<td colspan="2">
								${loginMember.userName}<%-- <%=loginMember.getUserName() %> --%>님 환영합니다.
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="내 정보보기" onclick="location.assign('${path }/member/memberView.do?userId=${loginMember.userId}<%-- <%=loginMember.getUserId()%> --%>')">
							</td>
							<td>
								<input type="submit" value="로그아웃" onclick="location.replace('${path}/logout.do')">
								<!-- replace 사용하는 이유 : 이전페이지로의 이동을 제한하기위해 --> 
							</td>
						</tr>
					</table>
			</c:if>
			<%--  <%
			if(loginMember==null){
			%> 
				<form id="loginFrm" action="<%=request.getContextPath()%>/loginfrm.do" method="post"
				 onsubmit="return fn_validation();">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" 
								placeholder="아이디 입력" value="<%=saveId!=null?saveId:""%>">
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="패스워드 입력">
							</td>
							<td>
								<input type="submit" value="로그인">
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId" <%=saveId!=null?"checked":"" %>>
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입" onclick="location.assign('<%=request.getContextPath() %>/member/enrollMember.do')">
								<!--  assign 은 다음 페이지로 이동하면서 현재 페이지를 히스토리에 남기고 replace 는 남기지 않습니다.
   									  이는 브라우저의 뒤로가기를 눌렀을 때 그 차이를 확실히 알 수 있습니다. -->
							</td>
						</tr>
					</table>
				</form>
				<%} else{%>
					<table id="logged-in">
						<tr>
							<td colspan="2">
								<%=loginMember.getUserName() %>님 환영합니다.
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="내 정보보기" onclick="location.assign('<%=request.getContextPath() %>/member/memberView.do?userId=<%=loginMember.getUserId()%>')">
							</td>
							<td>
								<input type="submit" value="로그아웃" onclick="location.replace('<%=request.getContextPath() %>/logout.do')">
								<!-- replace 사용하는 이유 : 이전페이지로의 이동을 제한하기위해 --> 
							</td>
						</tr>
					</table>
				<%} %> --%>
			</div>
			
			
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="">home</a></li>
					<li id="notice"><a href="${path }/notice/memberList.do">공지사항</a></li>
					<li id="board"><a href="${path }/board/boardList.do">게시판</a></li>
					<c:if test="${loginMember!=null&&loginMember.userId.equals('admin')}">
						<li id="mamberManage"><a href="${path }/admin/memberList.do">회원관리</a></li>
					</c:if>
					<%-- <%if(loginMember!=null&&loginMember.getUserId().equals("admin")) {%>
						<li id="mamberManage"><a href="<%=request.getContextPath()%>/admin/memberList.do">회원관리</a></li>
					<%} %> --%>
				</ul>
			</nav>
		</header>


		
		<script>
			const fn_validation=()=>{ /* form태그 logonFrm의 onsubmit */
				/* alert("제출함"); */
				const userId=$("#userId").val();
				if(userId.length<4){
					alert("아이디는 4글자 이상입니다.")
					$("#userId").val("");
					/* 공간 비우기 : 안 비우면 값이 남아있는 상태임*/
					$("#userId").focus();
					return false; 
					/* form실행을 막음 */
				}
				if($("#password").val().length<3){
					return false;
				}
			}

		</script>
	