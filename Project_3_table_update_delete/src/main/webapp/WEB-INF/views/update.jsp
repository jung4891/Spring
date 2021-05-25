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
        <form action="update_action"> 
            <label> 이름 입력:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <!-- value가 있을때는 placeholder는 무시된다. -->
            <!-- hidden은 view에서 사라지게 한다. -->
                <input type="text" name="name" placeholder="이름" value="${name }">
                <input type="hidden" name="idx" style="width: 13%" value="${idx }">
                
            </label>
            <label> 중간고사 성적: 
                <input type="number" name="midScore" value="${midScore }">
            </label>
            <label> 기말고사 성적: 
                <input type="number" name="finScore" value="${finScore }">
            </label>       
            <input type="submit"  value="수정 완료">
        </form>
        <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
       	<a href="/project_3/list" >목록보기</a> &nbsp;
    	<a href="/project_3" >홈으로</a>
    	</div>
    </section>

</body>
</html>