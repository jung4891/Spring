<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목록</title>
        <link rel="stylesheet" type="text/css" media="screen" 
    		href='${pageContext.request.contextPath}/resources/insert.css'> 

</head> 
<body>
    <section class="wrap">
        <form action="insert_action" method="GET"> <!-- method없어도 기본이 GET-->
            <label> 이름 입력: 
                <input type="text" name="student_name" placeholder="이름">
            </label>
            <label> 점수 입력: 
                <input type="number" name="score" placeholder="점수">
            </label>
            <input type="submit" value="입력 완료">
        </form>
    </section>
</body>
</html>