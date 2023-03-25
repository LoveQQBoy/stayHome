<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!--只要用contextRoot 就會幫你抓根目錄-->
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--載入css樣式-->
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet"/>


</head>
<body>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
      </a>

      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="${contextRoot}" class="nav-link px-2 link-secondary">首頁</a></li>
        <li><a href="${contextRoot}/household/readpage" class="nav-link px-2 link-dark">住戶資料管理</a></li>
        <li><a href="${contextRoot}" class="nav-link px-2 link-dark">還沒用</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">FAQs</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">About</a></li>
      </ul>
      
      
	
     <%--  <div class="col-md-3 text-end,edit-place"  style="display: flex;">
	        <input type="button" value="登入" class="btn btn-outline-primary me-2"
			onclick="javascrtpt:window.location.href='${contextRoot}/household/loginpage'" />
			
			<input type="button" value="註冊" class="btn btn-primary"
			onclick="javascrtpt:window.location.href='${contextRoot}/household/add'" />
			
			<input type="button" value="登出" class="btn btn-outline-danger"
			onclick="javascrtpt:window.location.href='${contextRoot}/household/logout'" />
			<h3>住戶:${name}</h3>
      </div>
      
      
       要實現 登入後  註冊跟登入消失  未登入時會顯現 --%>
     	<div class="col-md-3 text-end,edit-place"  style="display: flex;">
     			 <c:choose>
					<c:when test="${ name != null }">
						<a href="#" class="btn btn-secondary me-2">住戶:${name}</a>
						<input type="button" value="登出" class="btn btn-outline-danger"
						onclick="javascrtpt:window.location.href='${contextRoot}/household/logout'" />
					</c:when>
					<c:otherwise>
					        <input type="button" value="登入" class="btn btn-outline-primary me-2"
							onclick="javascrtpt:window.location.href='${contextRoot}/household/loginpage'" />
							
							<input type="button" value="註冊" class="btn btn-primary"
							onclick="javascrtpt:window.location.href='${contextRoot}/household/add'" />				
					</c:otherwise>
				</c:choose>
      	 </div>
    
    
    </header>
  </div>








<!--載入bootstrap + jquery-->
<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
<script src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
</body>
</html>