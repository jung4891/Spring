<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>  

<html>
<head>
	<title>Ajax</title>
	<script src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>  
	<!-- c:url 안쓰고 그냥 하드코딩으로 입력은 어떻게 하지??  -->
</head>
<body>
	<h1>Ajax 연습</h1>
	
	<button onclick="location.href='/test'">메인으로</button> <br><br><br>
	
	<!-- 
			AJAX(Asynchronous JavaScript and XML) 
			AJAX는 단순히 텍스트값만 넘길수도 있고, form을 넘길수도 있고, 파일 업로드와 같이 웹개발에서 중요한 역할을 담당한다.
			AJAX만 잘 알아도 개발하는데 시간단축이 된다.
			AJAX는 비동기식 방법으로 데이터에 접근하는 것이다. 쉽게 말하면 그냥 브라우저에서 새로고침하지 않고도 데이터값을 변경할수 있는 것이다.
			대표적으로 AJAX가 사용된 사례는 각종 포털사이트의 인기검색어 혹은 연관검색어이다.
	 -->
	 
	 <span class="subject"> 1. data를 전송해서 값을 return 받음. (가장기본) -> </span>
	 <button id="btn1">simpleAJAX</button>
	 <div id="result1"></div>
	 <div id="testRes"></div> <br> 
	 
	 <span class="subject"> 1-1. 페이지 로드시 HashMap 사용하여 값 가져오기 </span>
	 <p>안녕하세요 <span id="result1_1"></span>님</p>
	 <p>(version: <span id="result1_1_1"></span>)</p> <br>	 
	 
	 <span class="subject">	2. 입력한 data을 form태그로 전송해서 값 가져오기 -> </span>
	 <button id="btn2">serialize</button>
	 <form id="frm">
	    name : <input type="text" name="name" id="name"><br>
	    age : <input type="text" name="age" id="age">
	 </form>
	 <div id="result2"></div> <br>
	 
	 <span class="subject">	3. SQLite로 CRUD 구현 -> </span>
	 <button id="btn3" onclick="location.href='sqliteList'">SQLite</button>

</body>
</html> 

<script>

	// 1. 
	$('#btn1').on('click', function(){
		var dataSet = {
				name: "hyukjung",
				age: 32,
				test: 1
		}
		$.ajax({
            url: "requestObject",
            type: "POST",
            data: dataSet,						// 위 dataSet을 보내면 컨트롤러에서  person VO가 받아서 name과 age에 들어가게됨.
            success: function(data){			// data는 컨트롤러에서 return된 String값임.
                $('#result1').text(data);		// hyukjung 32 / test: 1
                $('#testRes').text("test");
            },
            error: function(){
                alert("simpleWithObject err");
            }
   		});
	});
	
	// 1-1. 
	// jackson library 추가해야함. -> json 형태로 데이터를 리턴해주는 애. ()
	//   추가 안하게 되면 406 에러가 뜨는데 그 원인은 서버에서 request가 
	//   알려준 content type에 맞는 response 를 생성할 수 없기 때문에 발생함
	$(function(){
		$.ajax({
			url: 'hello_api',
			success: function(data){
				$('#result1_1').text(data['name']);
				$('#result1_1_1').text(data['version']);
			}
		});
	});

	// 2. 
	// serialize() 메소드는 jquery가 가지고 있는 기본기능중 하나로 form의 객체들을 한번에 받을 수가 있다. 
	$('#btn2').on('click', function(){
			$.ajax({
	        url: "serialize",
	        type: "POST",
	        data: $("#frm").serialize(),					// serialize() 없으면 정상작동이 안된다.
	        success: function(data){
	            $('#result2').text(data);
	        },
	        error: function(){
	            alert("serialize err");
	        }
	    });
			// alert($("#frm").serialize());				// name=jung&age=32
			// alert($("#name").val())						// jung
			// alert($("#frm"));							// [object Object]
	});
	
</script>

<style>
.subject {
	font-weight: bold;
}

</style>
