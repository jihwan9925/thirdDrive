<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%-- <script src="<%=request.getContextPath() %>/js/jquery-3.7.0.min.js"></script> --%>

	<section id="enroll-container">
        <h2>회원 가입 정보 입력</h2>
        <form action="<%=request.getContextPath() %>/member/enrollMemberEnd.do" method="post" >
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder=" 4글자이상" name="userId" id="userId_" >
					<input type="button" onclick="fn_dublicateId();" value="중복확인">
				</td>
			</tr>
			
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" ><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password_2" ><br>
				</td>
			</tr>  
			<tr>
				<th>
				</th>
				<td>
					<!-- 비밀번호가 일치하지 않습니다. -->
					
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>	
				<input type="number" name="age" id="age"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" onclick="return fn_validate2();">
		<input type="reset" value="취소">
        </form>
    </section>
	
	 <script>
       /* const getChecked=()=>{
            let checkboxes = document.querySelectorAll('input[type="checkbox"]');
            let checkedValues = [];

            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    checkedValues.push(checkbox.value);
                }
            });

            console.log('체크된 값:', checkedValues)asd;
        } */
        const fn_validation2=()=>{ /* form태그 logonFrm의 onsubmit */
			/* alert("제출함"); */
			const userId=$("#userId_").val();
			if(userId.length<4)
				{
				alert("아이디는 4글자 이상입니다.");
				/* $("#userId_").val(""); */
				/* 공간 비우기 : 안 비우면 값이 남아있는 상태임*/
				$("#userId").focus();
				return false; 
				/* form실행을 막음 */
				}
			/* else if(!$("#password_").val().equals($("#password_2").val()))
				{
				console.log($("#password_").val());
				console.log($("#password_2").val());
				alert("비밀번호가 불일치합니다.");
				return false;
				}
			else return true; */
		}
        $("#password_2").keyup(e=>{
        	const password=$("#password_").val();
        	const passwordCheck=$(e.target).val();
        	let color, msg;
        	if(password==passwordCheck){
        		color="green";msg="비밀번호가 일치합니다.";
        		$("<p>").css("color",color).text(msg);
        	}else{
        		color="red";msg="비밀번호가 일치하지 않습니다.";
        		$("<p>").css("color",color).text(msg);
        	}
        	const td=$(e.target).parents("tr").next().find("td");
        	td.html(""); /* 없으면 값이 계속 늘어난다. */
        	$("<p>").css("color",color).text(msg).appendTo(td);
        	
        });
        
        const fn_dublicateId=()=>{
        	const userId=$("#userId_").val();
        	console.log(userId);
        	if(userId.length>=4){
        	open("<%=request.getContextPath()%>/member/idDuplicate.do?userId="+userId,"_blank","width=300px, height=200px")
        	//location이 아닌 이유 : 새창으로 열기 위하여 open 사용
        	
        	/* v : do?userId="+userId */
        	}else{
        		alert("아이디는 4글자이상 입력하세요!");
        	}
        }
        
        //ajax로 바꾸기
        $("#userId_").keyup(e=>{
			if(e.target.value.length>=4){
				$.get("<%=request.getContextPath()%>/ajax/idDuplicate.do?id="+$(e.target).val(),
				data=>{
						let msg="",css={};
						if(data==='true'){
							msg="사용가능한 아이디입니다.";
							css={color:"green"};
						}else{
							msg="사용불가능한 아이디입니다.";
							css={color:"red"};
						}
						const tr=$("<tr>");
						const td=$("<td colspan='2'>").text(msg).css(css);
						tr.append(td);
						if($(e.target).parents("tr").next().find("input").length==0){
							$(e.target).parents("tr").next().remove();							
						}
						$(e.target).parents("tr").after(tr);
					}
				)
			}
		})
    </script>
    
<%@ include file="/views/common/footer.jsp" %>
