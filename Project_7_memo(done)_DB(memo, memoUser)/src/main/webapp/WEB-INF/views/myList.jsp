<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 상단taglib 부분 spring 이미지 사용시 필요 -->
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>Main</title>
	<meta name="viewport" content="width-device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" media="screen"
		href="${pageContext.request.contextPath}/resources/list.css">
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
		<section class="wrap">
			<table>
				<caption text-align="left">
					<a>MY LIST</a>
				</caption>
				<colgroup>
					<col width="6%" />
					<col width="15%" />
					<col width="30%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="7%" />
					<col width="7%" />
				</colgroup>
				<thead>
					<tr>
						<th>no</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성일시</th>
						<th>수정일시</th>
						<th>작성자</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>${htmlStr }
				</tbody>
			</table>
			<button type="button" onClick="location.href='insert'" >글쓰기</button>
		</section>
	</div>
</body>

</html>
