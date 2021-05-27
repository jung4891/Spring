
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>입력창</title>
	<link rel="stylesheet" type="text/css" media="screen" href='${pageContext.request.contextPath}/resources/insert.css'> 
</head>
<body>
	    <section class="wrap">
        <form action="insert_data_action"> 
            <label> 이름 입력: 
                <input type="text" name="name" placeholder="이름">
            </label>
            <label> 중간고사 성적: 
                <input type="number" name="midScore" placeholder="중간점수">
            </label>
            <label> 기말고사 성적: 
                <input type="number" name="finScore" placeholder="기말점수">
            </label>       
            <input type="submit" value="입력 완료">
        </form>
    </section>
    <a href="/project_4" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>