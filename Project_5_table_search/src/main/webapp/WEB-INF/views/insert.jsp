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
        <form action="insert_action"> 
            <label> 이름: 
                <input type="text" name="name">
            </label>
            <label> 성별(남/여): 
                <input type="text" name="gender">
            </label>
            <label> 주소: 
                <input type="text" name="address">
            </label>    
            <label> 소속부서: 
                <input type="text" name="team" >
            </label>     
            <input type="submit" value="입력 완료">
        </form>
    </section>
    <a href="/project_5" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>