<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<meta charset="utf-8">
<title>message</title>
<meta name="viewport" content="width-device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/resources/main.css">
</head>
<body>
	<div class="top">
		<div class="wrapper">
			<ul class="top_menu">
				<li class="login"><a href="signup"><p>회원가입</p></a></li>
				<li class="login"><a href="login"><p>로그인</p></a></li>
				<li class="login"><a href="myList"><p>나의메모</p></a></li>
			</ul>
		</div>
	</div>
	<header>
		<div class="header-wrap">
			<a href="/memoprogram2">MemoKit</a>
		</div>
	</header>
	<nav>
		<ul>
			<li>
				<div class="menu">
					<a href="insert">OPEN MEMO NEW</a>
				</div>
			</li>
			<li>
				<div class="menu">
					<a href="allList">OPEN LIST</a>
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
		<section class="message_wrap">
			<a><h1>${m1}</h1></a>
		</section>
	</div>
</body>

</html>
