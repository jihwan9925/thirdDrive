<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String msg=(String)request.getAttribute("msg");/* msg:아이디, 패스워드가 일치하지 않습니다. */
String loc=(String)request.getAttribute("loc");/* loc(location):/ */
String script=(String)request.getAttribute("script");
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 메세지</title>
</head>
<body>
	
	<script>
		alert("<%=msg%>");
		<%=script!=null?script:""%>
		location.replace("<%=request.getContextPath()%><%=loc%>");
	</script>
	<!-- 브라우저/서버가 2번 읽기 때문에 ""가 추가적으로 필요함! -->

<!--x alert(<%=msg%>); -->
<!-- alert(아이디, 패스워드가 일치하지 않습니다.); -->
<!-- ㄴ(*유의) "아이디, 패스워드가 일치하지 않습니다."라는 변수명의 '변수'가 출력되려고 하면서 오류가 뜨는 원인이 됨!-->

<!--o alert("<%=msg%>"); -->
<!-- alert("아이디, 패스워드가 일치하지 않습니다."); -->

</body>
</html>