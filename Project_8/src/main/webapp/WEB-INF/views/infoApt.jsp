<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>목록</title>
    <link rel="stylesheet" type="text/css" media="screen" 
    		href='${pageContext.request.contextPath}/resources/list.css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>     	

</head>
<body>
    <section class="wrap">
    <h1>아파트별 입주자의 수</h1>
        <table style="table-layout: fixed">
            <thead>
                ${htmlStr1 };
            </thead>
            <tbody>  
            	${htmlStr2 };
            </tbody>
        </table>
        <br>
        <a href="/fin" style="font-size: large; margin-left: 90%">홈으로</a>
    </section>
</body>
</html>