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
		<h1>입주자관리 프로그램</h1>
	</center>
	<section class="main">
		<a href="create_table">입주자/아파트 테이블 생성</a>
		<a href="insert_member">회원가입</a>
		<a href="show_list">입주자목록</a>
		<a href="info_member">입주자 통계</a>
		<a href="insert_apart">아파트 생성</a>
		<a href="apart_list">아파트목록</a>
		<a href="info_apart">아파트 통계</a>
	</section>

</body>
</html>
