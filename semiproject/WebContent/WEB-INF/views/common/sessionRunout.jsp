<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Error Page</title>
</head>
<body>
	<div align="center">
	<h1>세션이 만료되었습니다.</h1>
	<button onclick="location.href='<%= request.getContextPath() %>'">홈으로 돌아가기</button>
	</div>
</body>
</html>