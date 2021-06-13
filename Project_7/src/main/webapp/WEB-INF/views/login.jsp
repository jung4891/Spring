<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 상단taglib 부분 spring 이미지 사용시 필요 -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${pageContext.request.contextPath}/resources/signup.css">
</head>
<body>
	<div class="top">
		<div class="wrapper">
			<ul class="top_menu">
				<li class="login"><a href="signup"><p>회원가입</p></a></li>
				<li class="login"><a href="login"><p>로그인</p></a></li>
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
				<h1>Login</h1>
			</div>
		</header>
		<section class="signup_wrap">
			<form action="login_action" method="GET">
				<h3>
					<label> 아이디 </label> 
					<span> 
						<input type="text" name="id" placeholder="아이디를 입력하세요" />
					</span>
				</h3>
				<h3>
					<label> 비밀번호 </label> 
					<span> 
						<input type="password" name="pwd" placeholder="비밀번호를 입력하세요" />
					</span>
				</h3>
				<br> <input type="submit" value="로그인" /><br>
			</form>
		</section>
	</div>
</body>
</html>
