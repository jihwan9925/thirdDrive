<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.web.board.model.vo.dto.Board,com.web.board.model.vo.dto.BoardComment" %>
<%

	Board b=(Board)request.getAttribute("board"); //boardViewServlet
	int ref=Integer.parseInt(request.getParameter("no")); //
	List<BoardComment> comments=(List)request.getAttribute("comment"); //boardViewServlet

%>

<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    
     div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    background-color:#3300FF;position:relative;top:-20px;}
        /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-update{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-update{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    /*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
</style>
   
		<section id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%=b.getBoardNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getBoardReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<%if(b.getBoardOriginal()!=null) {%>
					<td>
						파일이 없습니다.
					</td>
				<%}else { %>
					<td>
					</td>
				<%} %>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%=b.getBoardContent() %></td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			<%if(loginMember!=null&&(loginMember.getUserId().equals("admin")
			||loginMember.getUserId().equals(b.getBoardWriter()))) {%>
			<tr>
				<th colspan="2">
					<button>수정하기</button> <button>삭제하기</button>
				</th>
			</tr>
			<%} %>
			
		</table>
		<div id="comment-container">
			<div class="comment-editor">
				<form action="<%=request.getContextPath()%>/board/insertComment.do" method="post" class="select-form">
					<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>">
					<input type="hidden" name="level" value="1">
					<input type="hidden" name="boardCommentWriter" value="<%=loginMember!=null?loginMember.getUserId():""%>">
					<input type="hidden" name="boardCommentRef" value="0">
					 <!-- value="0" 설명이해안감 
					 ㄴ 일단 댓글과 답글은 별개의 태이블이 아닌 하나의 태이블에 있고, 구분을 댓글은 boardRef로, 답글은 boardCommentRef로 한다. 
					 ㄴ boardCommentRef는 시퀀스를 참조하기 떄문에 1부터 시작하는 숫자가 되고 value에 0을 넣게 되면 답글이 아닌 댓글를 의미하게 된다. 
					 ㄴ 댓글을 표현하기 위해 dao에서 0을 넣으면 boardCommentRef에선 없는 값이기 때문에 에러가 나고, 이를 삼항연산자로 해결한다.-->
					<textarea name="content" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<table id="tbl-comment">
		<%if(comments!=null) {
		for(BoardComment bc: comments){
			if(bc.getLevel()==1){%>
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
						<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
						<br>
						<span><%=bc.getBoardCommentContent() %></span>
					</td>
					<td>
					<%if(loginMember!=null){ %>
						<button class="btn-reply" name="boardCommentNo" value="<%=bc.getBoardCommentNo()%>">답글</button>
						<button class="btn-update">수정</button>
						<button class="btn-delete"  onclick="location.assign('<%=request.getContextPath() %>/board/BoardCommentDelete.do?ref=<%=bc.getBoardRef()%>&no=<%=bc.getBoardCommentNo()%>')">삭제</button>
					<%} %>
					</td>
				</tr>
			<%}else{ %>
				<tr class="level2">
					<td>
						<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
						<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
						<br>
						<span><%=bc.getBoardCommentContent() %></span>
					</td>
					<td>
						<%if(loginMember!=null){ %>
							<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">답글</button>
							<button class="btn-update" >수정</button>
							<button class="btn-delete" onclick="location.assign('<%=request.getContextPath() %>/board/BoardCommentDelete.do?ref=<%=bc.getBoardRef()%>&no=<%=bc.getBoardCommentNo()%>')">삭제</button>
						<%} %>
					</td>
				</tr>
			<%}
			}
		}%>
		</table>
   
    </section>
    
    <script type="text/javascript">
	$(".comment-editor textarea[name=content]").focus(e=>{
		if(<%=loginMember==null%>){
			alert("로그인 후 이용가능");
			$("#userId").focus();
		}
	});
	//수정을 누르면 input태그가 나와서 수정 가능하게(기존값을 value로 지정하도록)
	$(".btn-update").click(e=>{
		//위에 로직 복사
		const form = $(".comment-editor>form").clone();
		//level 2
		//필요한 것 : (변경할 값) , (수정할 commentNo{PK값}) , (게시판 번호{돌아오기위한 주소})
		const boardCommentNo = $(e.target).prev().val();
		//commentNo 요청
		const input = $("<input>").attr({"type":"hidden","name":"boardCommnetNo"}).val(boardCommentNo);
		form.append(input);
		//해당주소로 돌아오기위한 no값 요청
		const no = $("<input>").attr({"type":"hidden","name":"no"}).val(<%=ref%>);
		form.append(no);
		
		form.find("textarea").attr({"rows":"1","cols":"50"});
		form.attr("action","<%=request.getContextPath()%>/board/updateComment.do");
		const update = $(e.target).parents("tr").find("span");
		//level 조회
		console.log($(e.target).parents("tr").attr("class"));
		//기존값 가져오기
		const defaultText = update.text();
		form.find("textarea").val(defaultText);
		update.html(form);
		$(e.target).off("click");
	});	
	
	//form태그를 복사했기 때문에 등록버튼을 누르면 submit이 된다.
	$(".btn-reply").click(e=>{
		const tr = $("<tr>"); //js로 태그 생성하는 법 기억하기
		const td = $("<td>").attr("colspan","2");
		const boardCommentRef = $(e.target).val(); //boardcommentRef 가져오기
		const form = $(".comment-editor>form").clone(); //태그를 복사하는 기능
		form.find("textarea").attr("rows","1"); //.find() : 자식태그의 내용까지 조회가능한 함수
		form.find("input[name=level]").val("2");
		form.find("input[name=boardCommentRef]").val(boardCommentRef);
		td.css("display","none");
		td.append(form);
		tr.append(td);
		tr.insertAfter($(e.target).parents("tr")).children("td").slideDown(800);
		$(e.target).off("click"); //.on()의 반대개념, 계속 실행되는 것과 반대로 실행을 끈다.
	});
	</script>

<%@ include file="/views/common/footer.jsp"%>
