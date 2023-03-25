<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
				<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
					<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



						<%-- 使用spring form 要寫的 --%>
							<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
							<!DOCTYPE html>
							<html>


							<head>

								<meta charset="UTF-8">
								<title>意見反應</title>
							</head>

							<body>

								${success}
								<jsp:include page="../layout/navbar.jsp"></jsp:include>
								<!-- Start Breadcrumbs -->
								<div class="breadcrumbs">
									<div class="container">
										<h2>意見反應</h2>
										<p>你們的意見讓社區變得更好
										</p>
									</div>
								</div>
								<!-- End Breadcrumbs -->

								<div class="container container-body mt-5">
									<h1></h1>

									<div class="d-flex flex-row-reverse">
										<a class="btn btn-primary mb-2" href="${contextRoot}/feedback/add">發表主題</a>
									</div>

									<table class="table table-bordered align-middle text-center">
										<thead class="table-light">
											<tr style="height: 50px; text-align: center;vertical-align: middle;">
												<th scope="col">主題</th>
												<th scope="col">發布時間</th>
												<th scope="col">最後回覆時間</th>
												<th scope="col">回應</th>
												<c:choose>
													<c:when test="${managerPermission > 0}">
														<th scope="col">操作</th>
													</c:when>
													<c:otherwise>

													</c:otherwise>
												</c:choose>
											</tr>
										</thead>

										<tbody>

											<!-- 在controller 裡面的showFeedbackPage 方法，有用feedbackService.feedbackByPage(pageNumber) 
			  	再用 model.addAttribute("feedbackByPage", feedbackByPage); ，所以下方的items 欄位名稱要用feedbackByPage
			  	加上要取出裡面的資料 要再多打content ，var="fb" 的是可以換成簡寫fb之後呼叫 -->
											<c:forEach var="fb" items="${feedbackByPage.content}">
											
												<tr>
													<td>
													
														<a class="feedBack feedBack-font-color"
															href="${contextRoot}/feedback/replypage/${fb.feedbackId}"
															style="">${fb.feedbackName}</a>
														
															<p class="feedBack-p">
															</p>
														
													</td>
													<td>
														${fb.householdData.name}
														<p class="feedBack-p">
															<fmt:formatDate pattern="yyyy-MM-dd E HH:mm:ss"
																value="${fb.feedbackDate}" />
														</p>
													</td>
													<td>
														<c:if test="${(fn:length(fb.response)-1) >=0}">
															<c:forEach begin="${fn:length(fb.response)-1}"
																end="${fn:length(fb.response)-1}" items="${fb.response}"
																var="response">
																${response.householdData.name}
																<p class="feedBack-p">
																	<fmt:formatDate pattern="yyyy-MM-dd E HH:mm:ss"
																		value="${response.responseDate}" />
																</p>
															</c:forEach>
														</c:if>
													</td>
													<td id="findOenResponse${fb.feedbackId}">

													</td>


													<c:choose>
														<c:when test="${managerPermission > 0}">
															<td>

																<div class="d-inline-flex">
																	<!-- <input 隱藏欄位:type="hidden"， 送請求參數:name="id" 到controller的 feedback/editpage 方法 再用 RequestParam Integer id接住 ， 請求參數取得方法:value=fb.feedbackId> -->
																	<form action="${contextRoot}/feedback/editpage"
																		method="get">
																		<input type="hidden" name="id"
																			value="${fb.feedbackId}">
																		<input type="submit"
																			class="btn btn-outline-success btn-sm"
																			value="編輯">
																	</form>
																	<!-- 原生地form 沒辦法用delete請求  要多寫 input type="hidden" name="_method value="delete" /> -->
																	<form
																		action="${contextRoot}/feedback/deletefeedback"
																		method="post">
																		<input type="hidden" name="id"
																			value="${fb.feedbackId}">
																		<input type="hidden" name="_method"
																			value="delete">
																		<input type="submit"
																			class="btn btn-outline-danger btn-sm"
																			value="刪除">
																	</form>
																</div>

															</td>
														</c:when>
														<c:otherwise>

														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</tbody>
									</table>


									<%-- 不使用
										從1開始，到feedbackByPage.totalPages結束，每個頁碼的變數名稱為pageNumber。這通常用於顯示分頁的頁碼連結，使用戶可以輕鬆地瀏覽長列表或搜索結果的不同頁面。
										<c:forEach var="pageNumber" begin="1" end="${feedbackByPage.totalPages}">
										<c:choose>
											<c:when test="${feedbackByPage.number != pageNumber-1}">
												<a
													href="${contextRoot}/feedback/showfeedback?p=${pageNumber}">${pageNumber}</a>
											</c:when>

											<c:otherwise>
												${pageNumber}
											</c:otherwise>
										</c:choose>
										</c:forEach>
										--%>


										<div class="text-center">
											<%--從1開始，到feedbackByPage.totalPages結束，每個頁碼的變數名稱為pageNumber。這通常用於顯示分頁的頁碼連結，使用戶可以輕鬆地瀏覽長列表或搜索結果的不同頁面。
												--%>
												<c:forEach var="pageNumber" begin="1"
													end="${feedbackByPage.totalPages}">
													<c:choose>
														<c:when test="${feedbackByPage.number != pageNumber-1}">

															<a class="btn btn-success"
																href="${contextRoot}/feedback/showfeedback?p=${pageNumber}">${pageNumber}</a>

														</c:when>

														<c:otherwise>
															<a class="btn btn-outline-success"
																href="${contextRoot}/feedback/showfeedback?p=${pageNumber}">${pageNumber}</a>

														</c:otherwise>
													</c:choose>
												</c:forEach>
										</div>

								</div>
								<!-- footer -->
								<jsp:include page="../layout/footer.jsp"></jsp:include>

								<!-- Vendor JS Files -->
								<script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
								<script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
								<script
									src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
								<script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
								<script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

								<!-- Template Main JS File -->
								<script src="${contextRoot}/assets/js/main.js"></script>

								<!-- sweetAlert -->
								<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

								<script>
									window.onload = function () {
										feedback()
									}

									function feedback() {
										fetch("${contextRoot}/feedback/showfeedbackJson"
											, {
												method: 'get'
											}).then(response => {
												return response.json()
											}).then(data => {
												data.content.forEach(data => {
													let findOenResponseElement = document.getElementById("findOenResponse" + data.feedbackId)

													findOenResponse(data.feedbackId).then(data => {
														findOenResponseElement.innerHTML = data
													})
												})
											})

									}

									function findOenResponse(data) {
										return fetch("${contextRoot}/response/findOneResponse?feedbackId=" + data
											, {
												method: 'get'
											}).then(response => {
												return response.json()
											}).then(data => {

												return data
											})
									}

								</script>

								<c:if test="${not empty success}">
									<script>
										Swal.fire({
											icon: 'success',
											title: '發表成功',
											showConfirmButton: false,
											timer: 1500
										});
									</script>
								</c:if>
							</body>

							</html>