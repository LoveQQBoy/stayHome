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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript"
	src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
<script>
$(function() {
	$(":radio[value='${publicState}']").attr("checked", true);			
	
	$("#publicName").val(`${publicName}`);
	
})
</script>

</head>
<body>

	<jsp:include page="../layout/sideNavbar.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>後台公設管理列表</h2>
			<p></p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	<div class="container-right">
		<div class="row">

			<!-- ---------------------------程式區-------------------------------- -->

			<span class="text-danger fs-3">${insertSuccess} ${deleteSuccess}</span>

					<form method="get" action="${contextRoot}/public/publicList" >
						<div class="text-center m-4">							
							<label for='publicName' class="ms-5">公設名稱 :</label>	
							<input id="publicName" name="publicName" class="me-5"/>

							<input type="radio" name="publicState" value="" />全部	
							<input type="radio" name="publicState" value="1" />啟用
							<input type="radio" name="publicState" value="0" />停用

							<input type="submit" value="搜尋" class="ms-5"/>	
						</div>		
					</form>



			<div class="d-flex flex-row-reverse">				
				<a class="btn btn-primary mb-2"
					href="${contextRoot}/public/createPublic">新增公設</a>
				<a class="btn btn-info mb-2 me-2"
					href="${contextRoot}/public/appointmentListBack">返回預約列表</a>
			</div>



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
						<th>創建時間</th>
						<th>公設名稱</th>
						<th>開放日(星期)</th>
						<th>開放時間</th>
						<th>啟用狀態</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="apublic" items="${page.content}">
						<tr>
							<td><fmt:formatDate value="${apublic.date}" pattern="yyyy-MM-dd ,a hh:mm:ss" /></td>
							<td>${apublic.publicName}</td>
							<td>${apublic.daysOfWeek}</td>
							<td>${apublic.openingHour}~${apublic.closingHour}</td>
							
							<c:if test="${apublic.state=='0'}">
								<td><i class="fa fa-minus-square" style="font-size:25px;color:red"></i></td>
							</c:if>
							<c:if test="${apublic.state=='1'}">
								<td><i class="fa fa-check-square" style="font-size:25px;color:green"></i></td>
							</c:if>
														
							<td>
								<button type="button" class="btn btn-warning"
									onclick="location.href='${contextRoot}/public/modify/${apublic.id}'">修改</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<span>
				<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number != pageNumber-1}">
							<a href="${contextRoot}/public/publicList?p=${pageNumber}
							&publicState=${publicState}&publicName=${publicName}">${pageNumber}</a>
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