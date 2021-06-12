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
<!-- 	<script type="text/javascript">
  		$(document).on('click', '#test', function(event) {    	
			alert("테스트입니다!")
			alert($('a[name="test1"]').val())
		})
       
	</script> -->
</head>
<body>
    <section class="wrap">
    <h1>MEMBER LIST</h1>
        <table style="table-layout: fixed">
            <thead>
                <th>idx</th><th>아이디</th><th>비밀번호</th><th>이름</th><th>생년월일</th><th>주소</th><th>생성일</th><th>수정일</th><th>수정</th><th>삭제</th>
            </thead>
            <tbody>  
            	${htmlList }
            </tbody>
        </table>
        <br>
<!--         <a href="show_list" name="test1" value="abc" style="font-size: large; margin-left: 90%" id="test">테스트</a> -->
        <a href="/project_6" style="font-size: large; margin-left: 90%">홈으로</a>
    </section>
</body>
</html>