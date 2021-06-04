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
        <form action="login_action" method="post"> 
            <label> 아이디: 
                <input type="text" name="id" placeholder="아이디">
            </label>
            <label> 패스워드: 
                <input type="password" name="pwd" placeholder="패스워드">
            </label>
            
            <input type="submit" value="Sign in">
        </form>
    </section>
    <a href="/project_6" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>