<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String referer = (String)request.getHeader("REFERER"); 
	// System.out.println(referer); 	// http://localhost:8787/test/sqliteList
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>메세지</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <section class="wrap" style="width: 60%; margin: 0 auto; padding: 50px;">
        <p style="padding: 20px; background: #eee; border-radius: 5px; text-align: center;">
            ${msg }
            referer
        </p>
        <a href="sqliteList" style="margin-top: 50px;">이전페이지로</a>
    </section>
</body>
<script type="text/javascript">

</script>
</html>

