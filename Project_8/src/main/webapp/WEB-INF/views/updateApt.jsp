<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>수정창</title>
	<link rel="stylesheet" type="text/css" media="screen" href='${pageContext.request.contextPath}/resources/insert.css'> 
</head>
<body>
  	<section class="wrap">
  		<h1>UPDATE Apartment</h1>
        <form action="updateApt_action" method="get"> 
            <label> 아파트 이름: 
                <input type="text" name="name" placeholder="이름">
                <input type="hidden" name="idx" value="${idx }">
            </label>
            <input type="submit" value="수정 완료">
        </form>
        <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
       	<a href="/fin/apart_list" >아파트 목록보기</a> &nbsp;
    	<a href="/fin" >홈으로</a>
    	</div>
    </section>
</body>
</html>