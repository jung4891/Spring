<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메세지</title>
    <link rel="stylesheet" type="text/css" media="screen" href="insert.css">
</head> 
<body>
    <section class="wrap" style="width: 60%; margin: 0 auto; padding: 50px;">
        <p style="padding: 20px; background: #eee; border-radius: 5px; text-align: center;">
            ${msg }
        </p> <br>
        <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
       	<a href="/project_3/update?idx=${idx }" >다시 입력</a> &nbsp;
    	<a href="/project_3" >홈으로</a>
    	</div>
    </section>
</body>
</html>