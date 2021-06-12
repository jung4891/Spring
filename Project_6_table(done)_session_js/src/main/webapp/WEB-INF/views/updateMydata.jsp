<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보 수정</title>
	<link rel="stylesheet" type="text/css" media="screen" href='${pageContext.request.contextPath}/resources/insert.css'> 
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
  		$(document).on('click', '#submit_btn', function(event) {  
  		// $('#submit_btn').click(function(event) {  		-> 이건 왜 안되지????     	
		
			var pwd1 = $('input[name="new_pwd"]').val();
			var pwd2 = $('input[name="new_pwd2"]').val();
			if (pwd1 != pwd2) {
				alert("패스워드가 다릅니다. 다시 입력해주세요!")
			} else {
				$('#update_mydata').submit();
			}
		})
		
	/* submit_btn이 클릭되면 event 함수를 진행할꺼다.
       input type이 submit이 아닌 button이므로 action으로 안가고 자바스크립트로 빠진다. */
       
	</script>
</head>
<body>
  	<section class="wrap">
  		<h1>UPDATE MY DATA</h1>
        <form action="updateMydata_action" method="past" id="update_mydata"> 
            <input type="hidden" name="idx" value="${idx }">
            <input type="hidden" name="id" value="${id }">
            <label> 아이디: &nbsp 
            	<span>${id }</span>
            </label>
            <label> 새 비밀번호: &nbsp 
                <input type="password" name="new_pwd" placeholder="새 비밀번호">
            </label>
            </label>
            <label> 새 비밀번호 확인: &nbsp 
                <input type="password" name="new_pwd2" placeholder="새 비밀번호 확인">
            </label>
            <label> 이름: 
                <input type="text" name="new_name" value="${original_name }">
            </label>
            <label> 생일: 
                <input type="date" name="new_birthday" value="${original_birthday }">
            </label>                    
            <label> 주소: 
                <input type="text" name="new_address" value="${original_address }">
            </label>    
            <input type="button" value="수정 완료" id="submit_btn">
        </form>
        <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
    		<a href="/project_6" >홈으로</a>
    	</div>
    </section>
</body>
</html>