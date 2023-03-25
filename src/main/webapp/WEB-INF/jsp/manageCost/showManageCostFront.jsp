<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
				<script type="text/javascript">
				</script>
			</head>

			<body>
				<jsp:include page="../layout/navbar.jsp" />

				<!-- ======= Breadcrumbs ======= -->
				<div class="breadcrumbs">
					<div class="container">
						<h2>管理費</h2>
						<p>記得去繳費喔!</p>
					</div>
				</div>
				<!-- End Breadcrumbs -->

				<div class="container">
					<!-- ---------------------------程式區-------------------------------- -->
			<h1>管理費</h1>
			
			<div>
				<table class="table table-bordered border-dark align-middle text-center">
					<thead class="table-secondary">
						<tr>
							<th scope="col">期別</th>
							<th scope="col">標題</th>
							<th scope="col">應繳金額</th>
							<th scope="col">狀態</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="manageCost" items="${mcPage.content}">
							<tr>
								<td>${manageCost.period}</td>
								
								<td>${manageCost.manageCostList.mcTitle}</td>
								
								<td>${manageCost.money}</td>
								
								<td>
									<c:choose>
										<c:when test="${manageCost.payTime!=null}">
											<span>繳費時間:<fmt:formatDate value="${manageCost.payTime}"
												pattern="yyyy/MM/dd HH:mm:ss" /></span>
										</c:when>
										<c:when test="${manageCost.manageCostList.mcState == 0}">
											<span class="text-danger">未開放</span>
										</c:when>
										<c:otherwise>
											<form action="${contextRoot}/ECPayServer" method="post">
												<input type="hidden" name="mcId" value="${manageCost.id}" />
												<input type="hidden" name="TotalAmount" value="${manageCost.money}" />
												<input type="hidden" name="ItemName" value="${manageCost.manageCostList.mcTitle}" />
												<input type="hidden" name="TradeDesc" value="${manageCost.manageCostList.mcTitle}" />
												<button type="submit" class="btn btn-primary">去繳費</button>
											</form>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- ---------------------------程式區-------------------------------- -->
				</div>

				<!-- footer -->
				<jsp:include page="../layout/footer.jsp"></jsp:include>

				<!-- Vendor JS Files -->
				<script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
				<script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
				<script src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
				<script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
				<script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

				<!-- Template Main JS File -->
				<script src="${contextRoot}/assets/js/main.js"></script>

				<!-- sweetAlert -->
				<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
			</body>

			</html>