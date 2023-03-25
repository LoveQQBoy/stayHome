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
	$(function() {

		$(":radio[value='${replyState}']").attr("checked", true);

		$("#date").val(`${date}`);

	})
</script>

</head>

<body>

	<jsp:include page="../layout/navbar.jsp" />

	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>住戶維修列表</h2>
			<p>有看到任何器材損壞了嗎?都可以來這邊回報喔!</p>
		</div>
	</div>
	<!-- End Breadcrumbs -->

	<!-- ---------------------------程式區-------------------------------- -->

	<span class="text-danger fs-3">${insertSuccess}</span>

	<form method="get" action="${contextRoot}/repair/residentList">
		<div class="text-center mt-4">
			<span class="me-2">日期 : </span> 
			<input type="date" id="date" name="date" />之後
			<input type="radio" name="replyState" value="" class="ms-5"/>全部 
			<input type="radio" name="replyState" value="1" />已回覆
			<input type="radio" name="replyState" value="0" />未回覆 
			<input type="submit" value="搜尋" class="ms-5" />
		</div>
	</form>

	<div class="d-flex flex-row-reverse">
		<a class="btn btn-warning mb-2" href="${contextRoot}/repair/report">通報維修</a>
	</div>

	<span>共${page.totalElements}筆， 
		<c:choose>
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
				<th>維修編碼</th>
				<th>時間</th>
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
					
					<c:if test="${repair.replyState == '1'}">
						<td>
							<i class="material-icons" style="font-size:25px;color:green">check</i>
						</td>
						<td>
							<fmt:formatDate value="${repair.replyTime}" pattern="yyyy-MM-dd ,a hh:mm:ss" />
						</td>
					</c:if>
					<c:if test="${repair.replyState == '0'}">
						<td>
							<i class="material-icons" style="font-size:25px;color:red">close</i>
						</td>
						<td>
							-
						</td>
					</c:if>
				
					<td>
						<button type="button" class="btn btn-success"
							onclick="location.href='${contextRoot}/repair/${repair.id}'">詳細資料</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
		<c:choose>
			<c:when test="${page.number != pageNumber-1}">
				<a
					href="${contextRoot}/repair/residentList?
					p=${pageNumber}&date=${date}&replyState=${replyState}"
					class="btn btn-outline-success">${pageNumber}</a>
			</c:when>

			<c:otherwise>
				<div class="btn btn-success">${pageNumber}</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<!-- ---------------------------程式區-------------------------------- -->

	<!-- footer -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<!-- Vendor JS Files -->
	<script
		src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
	<script
		src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="${contextRoot}/assets/js/main.js"></script>
</body>

</html>