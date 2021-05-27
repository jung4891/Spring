<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>목록</title>
    <link rel="stylesheet" type="text/css" media="screen" 
    		href='${pageContext.request.contextPath}/resources/list.css'> 	
</head>
<body>
    <section class="wrap">
        <table>
            <thead>
                <th>idx</th><th>이름</th><th>성별</th><th>주소</th><th>소속부서</th><th></th><th></th>
            </thead>
            <tbody>  
            	${htmlList }
            </tbody>
        </table>
        <br>
        <a href="/project_5" style="font-size: large; margin-left: 90%">홈으로</a>
    </section>
</body>
</html>