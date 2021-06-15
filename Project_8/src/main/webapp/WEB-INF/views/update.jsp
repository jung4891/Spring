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
  		<h1>UPDATE MY DATA</h1>
        <form action="update_action" method="get"> 
            <label> 이름: 
                <input type="text" name="name" value="${name }" placeholder="이름">
                <input type="hidden" name="idx" value="${idx }">
            </label>
            <label> 나이: 
                <input type="number" name="age" value="${age }"placeholder="나이">
            </label>                    
            <label> 성별: 
                <input type="text" name="gender" value="${gender }" placeholder="성별(남/여)">
            </label>      
            	${htmlList }            
            <input type="submit" value="수정 완료">
        </form>
        <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
       	<a href="/fin/show_list" >목록보기</a> &nbsp;
    	<a href="/fin" >홈으로</a>
    	</div>
    </section>
</body>
</html>