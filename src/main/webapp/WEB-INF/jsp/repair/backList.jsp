<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript"
	src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
<script>
$(function(){
	
	$(":radio[value='${replyState}']").attr("checked", true);			
	
	$("#date").val(`${date}`);
	
	$("#repairName").val('${repairName}');
	
	$("#phone").val("${phone}");
	
})

</script>

</head>
<body>

	<jsp:include page="../layout/sideNavbar.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>後台維修列表</h2>
			<p>有看到任何器材損壞了嗎?都可以來這邊回報喔!</p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	<div class="container-right">
		<div class="row">
		
		<!-- ---------------------------程式區-------------------------------- -->		
								
		<span class="text-danger fs-3">${replySuccess}</span>
		
		<form method="get" action="${contextRoot}/repair/backList" >
			<div class="text-center m-4">
				<label for='date' class="me-2">日期 :</label>				
				<input type="date" id="date" name="date"/>之後
				
				<label for='repairName' class="ms-5">姓名 :</label>	
				<input type="text" id="repairName" name="repairName" class="me-5"/>
				
				<label for='phone'>電話 :</label>	
				<input type="text" id="phone" name="phone" class="me-5"/>
				
				<input type="radio" name="replyState" value="" />全部	
				<input type="radio" name="replyState" value="1" />已回覆
				<input type="radio" name="replyState" value="0" />未回覆
				
				<input type="submit" value="搜尋" class="ms-5"/>	
			</div>		
		</form>
				
		<span>共${page.totalElements}筆，		
			<c:choose>
				<c:when test="${page.totalPages == 0 || (page.number-1) > page.totalPages}">
					此頁顯示0筆
				</c:when>
				
				<c:when test="${page.totalPages > (page.number+1)}">
					此頁顯示5筆
				</c:when>
				
				<c:otherwise>
					此頁顯示${page.totalElements-page.number*5}筆
				</c:otherwise>
			</c:choose>				
		</span>

		<table class="table table-bordered align-middle text-center">
			<thead class="table-secondary">
				<tr>
					<th>維修編碼</th>
					<th>時間</th>
					<th>姓名</th>
					<th>電話</th>
					<th>回覆</th>
					<th>回覆時間</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="repair" items="${page.content}">
					<tr>
						<td>${repair.id}</td>
						<td><fmt:formatDate value="${repair.date}"
								pattern="yyyy-MM-dd ,a hh:mm:ss" /></td>
						<td>${repair.resident.name}</td>
						<td>${repair.resident.phone}</td>
																		
						<c:if test="${repair.replyState == '1'}">
							<td >
								<i class="material-icons" style="font-size:25px;color:green">check</i>
							</td>
							<td >
								<fmt:formatDate value="${repair.replyTime}" pattern="yyyy-MM-dd ,a hh:mm:ss" />
							</td>
							<td >
<!-- 								<button type="button" class="btn btn-success" -->
<%-- 								onclick="location.href='${contextRoot}/repair/reply/${repair.id}/${page.number+1}'">維修回報</button> --%>
								<button type="button" class="btn btn-success"
								onclick="location.href='${contextRoot}/repair/${repair.id}'">詳細資料</button>
							</td>
						</c:if>
						<c:if test="${repair.replyState == '0'}">
							<td >
								<i class="material-icons" style="font-size:25px;color:red">close</i>
							</td>
							<td >
								-
							</td>
							<td >
								<button type="button" class="btn btn-danger"
								onclick="location.href='${contextRoot}/repair/reply/${repair.id}'">維修回報</button>
							</td>							
						</c:if>
																		
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<span>
			<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
				<c:choose>
					<c:when test="${page.number != pageNumber-1}">
						<a href="${contextRoot}/repair/backList?p=${pageNumber}
						&date=${date}&replyState=${replyState}&repairName=${repairName}&phone=${phone}">${pageNumber}</a>
					</c:when>
	
					<c:otherwise>
						${pageNumber}
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</span>
				
		<!-- ---------------------------程式區-------------------------------- -->
		
		</div>
	</div>

</body>
</html>