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
    		href='${pageContext.request.contextPath}/resources/list.css'> 
</head> 
<body>
    <section class="wrap">
        <table>
            <thead>
                <th>idx</th><th>이름</th><th>중간점수</th><th>기말점수</th><th>총점</th><th>생성일</th><th></th><th></th>
            </thead>
            <tbody>  
            	${htmlList }

<!--             	
                <tr>
                    <td>1</td><td>송혁중</td><td>100</td><td>2021-05-18</td>
                </tr>
                <tr>
                    <td>2</td><td>song</td><td>90</td><td>2021-05-18</td>
                </tr>
                <tr>
                    <td>3</td><td>hyuk</td><td>80</td><td>2021-05-20</td>
                </tr> 
-->
            </tbody>
        </table>
        <br>
        <a href="/project_3" style="font-size: large; margin-left: 90%">홈으로</a>
    </section>

</body>
</html>