<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 상단taglib 부분 spring 이미지 사용시 필요 -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>Update User</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${pageContext.request.contextPath}/resources/signup.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
  		$(document).on('click', '#submit_btn', function(event) {  	
			var pwd1 = $('input[name="pwd1"]').val();
			var pwd2 = $('input[name="pwd2"]').val();
			if (pwd1 != pwd2) {
				alert("패스워드가 다릅니다. 다시 입력해주세요!")
				$('input[name="pwd1"]').val("")
				$('input[name="pwd2"]').val("")
			} else {
				$('#update_user').submit();
			}
		})
		
		function unregister_event() {
			if (confirm("정말 탈퇴하시겠습니까??") == true){    // 확인
				location.href="/memo/login";
			} else{   //취소
			    return;
			}
		}
       
	</script>
</head>
<body>
	<div class="top">
		<div class="wrapper">
			<ul class="top_menu">
				<li class="login"><a href="logout"><p>로그아웃</p></a></li>
				<li class="login"><a href="myMemo"><p>나의메모</p></a></li>
			</ul>
		</div>
	</div>
	<header>
		<div class="header-wrap">
			<a href="/memo">Memo</a>
		</div>
	</header>
	<nav>
		<ul>
			<li>
				<div class="menu">
					<a href="insert">NEW MEMO</a>
				</div>
			</li>
			<li>
				<div class="menu">
					<a href="selectAll">OPEN LIST</a>
				</div>
			</li>
			<li>
				<div class="menu">
					<a href="create">CREATE TABLE</a>
				</div>
			</li>
		</ul>
	</nav>
	<div class="main_bottom">
		<header>
			<div>
				<h1>Update User</h1>
			</div>
		</header>
		<section class="signup_wrap">
			<form action="updateUser_action" method="POST" id="update_user">
				<h3>
					<label> 아이디 </label>
					<span> 
						<input type="text" name="id" value="${id }" readonly="readonly" />
						<input type="hidden" name="idx" value="${userIdx }" />
					</span>
				</h3>
				<h3>
					<label> 비밀번호 </label> 
					<span> 
						<input type="password" name="pwd1" placeholder="새 비밀번호" />
					</span>
				</h3>
				<h3>
					<label> 비밀번호 확인 </label> 
					<span> 
						<input type="password" name="pwd2" placeholder="비밀번호 확인" />
					</span>
				</h3>
				<h3>
					<label> 이름 </label> 
					<span> 
						<input type="text" name="name" value="${name }" />
					</span>
				</h3>
				<h3>
					<label> 생년월일 </label> 
					<span> 
				 		<input type="date" name="birthday" value="${birthday }"/>
					</span>
				</h3>
				<h3>
					<label> 주소 </label> 
					<span> 
					<input type="text" name="address" value="${address }"/>
					</span>
				</h3>
				<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<input type="button" value="수정하기" id="submit_btn" style="display: inline; border-radius: 20px; height: 50px; font-size: 1.4em;"> &nbsp&nbsp
				<input type="button" value="회원탈퇴" onclick="unregister_event();" style="display: inline; border-radius: 20px; height: 50px; font-size: 1.4em;"><br>
			</form>
		</section>
	</div>
</body>
</html>
