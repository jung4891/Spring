<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 상단taglib 부분 spring 이미지 사용시 필요 -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>Update</title>
	<meta name="viewport" content="width-device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${pageContext.request.contextPath}/resources/insert.css">
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
					<a href="create">CREATE TABLE</a>
				</div>
			</li>
			<li>
				<div class="menu">
					<a href="insert">NEW MEMO</a>
				</div>
			</li>
			<li>
				<div class="menu">
					<a href="select">OPEN LIST</a>
				</div>
			</li>

		</ul>
	</nav>
	<div class="main_bottom">
		<header>
			<div>
				<h1>Update Memo</h1>
			</div>
		</header>
		<section class="data_insert">
			<form action="update_action" method="POST">
				<table width="100%">
					<tr>
						<td><h3>제목</h3></td>
						<td><input type="text" name="title" value="${title }" ></td>
						<td><input type="hidden" name="idx" value="${idx }" ></td>
					</tr>
					<tr>
						<td><h3>작성자</h3></td>
						<td><input type="text" name="writer" value="${writer }" readonly="readonly"></td>
						
					</tr>
					<tr>
						<td><h3>내용</h3></td>
						<td id="bottom"><textarea name="content" value=""  cols="55" rows="10" >${content }</textarea></td>
					</tr>

				</table>
				<input type="submit" value="수정하기" class="submit_btn">
			</form>
		</section>
	</div>
</body>
</html>
