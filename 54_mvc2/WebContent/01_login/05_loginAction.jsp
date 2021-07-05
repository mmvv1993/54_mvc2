<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${ isLogin eq true }">
			<script>
				alert("${sessionScope.memId}님 환영합니다!");
				location.href='_01_main';
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert('아이디와 패스워드 확인!!!!!!!!!');
				history.go(-1);
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>