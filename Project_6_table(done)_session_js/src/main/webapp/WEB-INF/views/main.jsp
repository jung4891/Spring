<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Main</title>
    <link rel="stylesheet" type="text/css" media="screen" 
    		href='${pageContext.request.contextPath}/resources/main.css'>
</head>
<body bgcolor=91A8d0 >
	<br> <br>
	<center>
		<h1>회원관리 프로그램</h1>
	</center>
	<section class="main">
		<a href="create_table">회원테이블 생성</a>
		<a href="insert_data">회원가입</a>
		<a href="login">로그인</a>
		<a href="show_list">회원목록(회원전용)</a>
		<a href="update_mydata">내 정보 변경(회원전용)</a>
		<a href="search">회원검색</a>
		<a href="logout">로그아웃</a>

	</section>

</body>
</html>
