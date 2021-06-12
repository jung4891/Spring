<%@ page language="java" contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="stylesheet" type="text/css" media="screen" href='${pageContext.request.contextPath}/resources/insert.css'> 
</head>
<body>
	    <section class="wrap">
	    <h1>SIGN IN</h1>
        <form action="login_action" method="post"> 
            <label> 아이디 <br>  
                <input type="text" name="id" placeholder="id">
            </label>
            <label> 비밀번호 <br>
                <input type="password" name="pwd" placeholder="pwd">
            </label>
            
            <input type="submit" value="로그인">
        </form>
    </section>
    <a href="/project_6" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>


</html>