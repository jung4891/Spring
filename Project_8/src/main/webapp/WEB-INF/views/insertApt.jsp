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
	    <h1>아파트 생성</h1>
        <form action="insertApt_action" method="post"> 
            <label> 아파트 이름: 
                <input type="text" name="name" placeholder="이름">
            </label>
            <input type="submit" value="입력 완료">
        </form>
    </section>
    <a href="/fin" style="font-size: large; font-weight: bold; margin-left: 60%;">홈으로</a>
</body>
</html>