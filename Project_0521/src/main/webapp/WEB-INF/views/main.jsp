<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<title>Main</title>
    <link rel="stylesheet" type="text/css" media="screen" 
   		href='${pageContext.request.contextPath}/resources/main.css'>
</head>
<body>
    <section class="wrap">
        <a href="create_table">테이블 생성</a>
        <a href="insert">성적 입력</a>
        <a href="list">성적 확인</a>
    </section>
</body>
</html>
