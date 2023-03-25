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

		$(":radio[value='${apState}']").attr("checked", true);

		$("#publicName").val(`${publicName}`);
		$("#name").val(`${name}`);
		$("#phone").val(`${phone}`);
		$("#date").val(`${date}`);

	})
</script>

</head>

<body>

	<jsp:include page="../layout/sideNavbar.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>後台預約公設列表</h2>
			<p></p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	<div class="container-right">
		<div class="row">

			<!-- ---------------------------程式區-------------------------------- -->

			<span class="text-danger fs-3">${insertSuccess}</span>

			<form method="get" action="${contextRoot}/public/appointmentListBack">
				<div class="text-center mt-4">
					<label for='publicName'>公設名稱 :</label> <input class="me-2"
						id="publicName" name="publicName" /> <label for='name'
						class="ms-2">姓名 :</label> <input type="text" id="name" name="name"
						class="me-2" /> <label for='phone'>電話 :</label> <input type="text"
						id="phone" name="phone" class="me-2" /> <br>
					<div class="mt-2">
						<span class="ms-5">預約日期 : </span> <input type="date" id="date"
							name="date" />之後 <input type="radio" name="apState" value=""
							class="ms-5" />全部 <input type="radio" name="apState" value="已預約" />已預約
						<input type="radio" name="apState" value="取消預約" />取消預約 <input
							type="radio" name="apState" value="預約結束" />預約結束 <input
							type="submit" value="搜尋" class="ms-5" />
					</div>
				</div>
			</form>

			<div class="d-flex flex-row-reverse">
				<a class="btn btn-primary mb-2"
					href="${contextRoot}/public/publicList">公設管理</a>
			</div>

			<span>共${page.totalElements}筆， <c:choose>
					<c:when test="${page.totalPages == 0}">
					此頁顯示0筆
			</c:when>

					<c:when test="${page.totalPages != (page.number+1)}">
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
						<th>時間</th>
						<th>公設名稱</th>
						<th>姓名</th>
						<th>電話</th>
						<th>預約日期</th>
						<th>時段</th>
						<th>人數</th>
						<th>預約狀態</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="appo" items="${page.content}">
						<tr>
							<td><fmt:formatDate value="${appo.date}"
									pattern="yyyy-MM-dd ,a hh:mm:ss" /></td>
							<td>${appo.public_.publicName}</td>
							<td>${appo.resident.name}</td>
							<td>${appo.resident.phone}</td>
							<td>${appo.appointmentDate}</td>
							<td>${appo.appointmentTime}</td>
							<td>${appo.appointmentNumber}</td>
							<td>${appo.appointmentState}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<span> <c:forEach var="pageNumber" begin="1"
					end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number != pageNumber-1}">
							<a
								href="${contextRoot}/public/appointmentListBack?p=${pageNumber}&date=${date}&apState=${apState}&publicName=${publicName}&name=${name}&phone=${phone}"
								class="btn btn-outline-success">${pageNumber}</a>
						</c:when>

						<c:otherwise>
							<div class="btn btn-success">${pageNumber}</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</span>

			<!-- ---------------------------程式區-------------------------------- -->

		</div>
	</div>

</body>
</html>