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
            <label> 아이디: 
                <input type="text" name="id" placeholder="아이디">
            </label>
            <label> 패스워드: 
                <input type="password" name="pwd" placeholder="패스워드">
            </label>
            <label> 이름: 
                <input type="text" name="name" placeholder="이름">
            </label>
            <label> 생일: 
                <input type="date" name="birthday" placeholder="생일">
            </label>                    
            <label> 주소: 
                <input type="text" name="address" placeholder="주소">
            </label>    
            <input type="submit" value="입력 완료">
        </form>
    </section>
    <a href="/project_6" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>