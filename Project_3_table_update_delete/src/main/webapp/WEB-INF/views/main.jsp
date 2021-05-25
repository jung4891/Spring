<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" type="text/css" media="screen" 
    		href='${pageContext.request.contextPath}/resources/main.css'>
</head> 							   	<!-- webapp/resources/main.css -->
<body>
    <section class="wrap">
        <a href="create_table">테이블 생성</a>
        <a href="insert">데이터 입력</a> 
        <a href="list">목록보기</a>
    </section>
</body>
</html>