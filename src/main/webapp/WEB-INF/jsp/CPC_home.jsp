<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首頁</title>
</head>
<body>
<jsp:include page="House\CPC_navbar.jsp"></jsp:include>
	<div class="container">
		<h1>首頁</h1>
		
	
		${pid}
		
	
	</div>
</body>
</html>