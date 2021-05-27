<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>업데이트</title>
        <link rel="stylesheet" type="text/css" media="screen" 
    		href='${pageContext.request.contextPath}/resources/insert.css'> 

</head> 
<body>
    <section class="wrap">
        <form action="update_action"> 
            <label> 이름 입력:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="hidden" name="idx" value="${idx }">
                <input type="text" name="name" value="${name }">
            </label>
            <label> 중간고사 성적: 
                <input type="number" name="mid" value="${midScore }">
            </label>
            <label> 기말고사 성적: 
                <input type="number" name="fin" value="${finScore }">
            </label>       
            <input type="submit"  value="수정 완료">
        </form>
        
        <div style="font-size:large; font-weight:bold; text-align: right;"> <br>
       	<a href="/project_4/show_list" >목록보기</a> &nbsp;
    	<a href="/project_4" >홈으로</a>
    	</div>
    </section>

</body>
