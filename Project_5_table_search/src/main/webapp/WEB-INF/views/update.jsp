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
        <form action="update_action"> 
            <label> 이름: 
                <input type="text" name="name" value="${name }">
                <input type="hidden" name="idx" value="${idx }">
            </label>
            <label> 성별(남/여): 
                <input type="text" name="gender" value="${gender }">
            </label>
            <label> 주소: 
                <input type="text" name="address" value="${address }">
            </label>    
            <label> 소속부서: 
                <input type="text" name="team" value="${team }">
            </label>     
            <input type="submit" value="수정 완료">
        </form>
                <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
       	<a href="/project_5/show_list" >목록보기</a> &nbsp;
    	<a href="/project_5" >홈으로</a>
    	</div>
    </section>
</body>
</html>