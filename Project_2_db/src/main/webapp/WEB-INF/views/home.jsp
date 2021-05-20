<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  위 UTF8은 자바, jsp 쪽 아래 charset은 HTML에 적용됨 -->
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<a href="create">테이블 생성</a> <br><br>
<a href="insert">데이터 입력</a>
<!-- Ajax 맹키로 /create으로 서버에 요청하는 것임.. -->

</body>
</html>
