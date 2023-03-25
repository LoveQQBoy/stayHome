<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/navbar.jsp"/>
		<form method="get" action="${contextRoot}/memberLogin/login">
		信箱<input type="text" name="email" value="hello@Wrold">
		密碼<input type="text" name="password" value="988">
		<button type="submit">提交</button>
		</form>
</body>
</html>