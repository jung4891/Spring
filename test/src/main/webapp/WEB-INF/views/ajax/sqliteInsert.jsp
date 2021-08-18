<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 존나 골때리게 안됨. 아래에서 똑같이 복붙하면 작동함.  
<script type="text/javascript">
	$('#s111').on('click', function(event){
		alert("aa");
		var name = $("#name").val();
		var age = $("#age").val();
		if (name && age) {								// 개신기함.
			$.ajax({
	            url:'insert_api',
	            data: {"name": name, "age": age},
	            success:function(data){
	                alert(data['msg']);
	            }
	        });
		} else {
			alert("데이터를 입력해주세요.");
		}
	});
</script> -->

</head>
<body>
	<input type="text" id="name" placeholder="이름" />
	<input type="number" id="age" placeholder="나이" />
	<button id="send">전송</button> <br><br>
	<a href="javascript:history.back()">이전페이지로</a>
</body>
<script type="text/javascript">
	$('#send').on('click', function(event){
		var name = $("#name").val();
		var age = $("#age").val();
		if (name && age) {								// 개신기함.
			$.ajax({
	            url:'insertPersonDB',
	            data: {"name": name, "age": age},
	            success:function(data){
	                alert(data['msg']);
	            }
	        });
		} else {
			alert("데이터를 입력해주세요.");
		}
	});
</script>
</html>