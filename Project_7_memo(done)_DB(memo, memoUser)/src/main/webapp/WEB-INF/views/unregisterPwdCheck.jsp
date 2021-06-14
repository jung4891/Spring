<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 상단taglib 부분 spring 이미지 사용시 필요 -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>비밀번호 확인</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${pageContext.request.contextPath}/resources/signup.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		
	function unregister_event() {
 		var pwd1 = $('input[name="input_pwd"]').val();
		var pwd2 = $('input[name="login_pwd"]').val();
		
		if (pwd1 != pwd2) {		// js는 문자열 ==로 가능함.
			alert("비밀번호가 틀렸습니다. 탈퇴를 하시려면 다시 입력해주세요.")
			$('input[name="input_pwd"]').val("")
		} else {
			location.href="/memo/unregister_action";
		}
	}
	// 탈퇴하기 input type을 submit으로하면 위 if문 끝나고 무조건 action으로 넘어감. 
	// button으로 해야함!!
       
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
				<h1>비밀번호 재확인</h1>
			</div>
		</header>
		<section class="signup_wrap">
			<form action="unregister_action" method="GET">
				<h3>
					<label> 비밀번호 입력 </label> 
					<span> 
						<input type="password" name="input_pwd" placeholder="비밀번호를 입력하세요" />
						<input type="hidden" name="login_pwd" value="${login_pwd }" />
					</span>
				</h3>
				<br> <input type="button" value="탈퇴하기" onclick="unregister_event();"/><br>
			</form>
		</section>
	</div>
</body>
</html>
