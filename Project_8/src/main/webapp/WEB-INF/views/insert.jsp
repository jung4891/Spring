<%@ page language="java" contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>입주자 등록</title>
	<link rel="stylesheet" type="text/css" media="screen" href='${pageContext.request.contextPath}/resources/insert.css'> 
</head>
<body>
	    <section class="wrap">
	    <h1>SIGN UP</h1>
        <form action="insert_action" method="post"> 
            <label> 이름: 
                <input type="text" name="name" placeholder="이름">
            </label>
            <label> 나이: 
                <input type="number" name="age" placeholder="나이">
            </label>                    
            <label> 성별: 
                <input type="text" name="gender" placeholder="성별(남/여)">
            </label>    
            <input type="submit" value="입력 완료">
        </form>
    </section>
    <a href="/fin" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>