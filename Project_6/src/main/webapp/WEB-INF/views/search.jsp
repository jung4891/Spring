<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>검색창</title>
	<link rel="stylesheet" type="text/css" media="screen" href='${pageContext.request.contextPath}/resources/insert.css'> 
</head>
<body>
	    <section class="wrap">
        <form action="search_action"> 
            <label> 검색할 이름: 
                <input type="text" name="name">
            </label>
            <input type="submit" value="검색">
        </form>
    </section>
    <a href="/project_5" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>