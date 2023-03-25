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
						<h2>投票資訊</h2>
						<p>所有的投票內容都在這裡喔!
						</p>
					</div>
				</div>
				<!-- End Breadcrumbs -->

				<div class="container w-75">
					<!-- ---------------------------程式區-------------------------------- -->

					<div class="container my-5">
						<div class="row">
							<div class="col-6">
								<h2 style="display: inline-block;">所有投票列表</h2> <button class="get-started-btn2"
									style="display: inline-block;"
									onclick='location.href="${contextRoot}/vote/voteFrontPage_TLK?useState=1"'>投票中</button>
								<button class="get-started-btn2" style="display: inline-block;"
									onclick='location.href="${contextRoot}/vote/voteFrontPage_TLK?useState=2"'>已結束</button>
							</div>
							<!-- 頁數 -->
							<!-- end="${ vaPage.number+3 > vaPage.totalPages ? vaPage.totalPages : vaPage.number+3}"> -->
							<div class="offset-1 col-5">
								<a class="get-started-circle"
									href="${contextRoot}/vote/voteFrontPage_TLK?page=1">&lt;</a>
								<c:forEach var="pageNum" begin="${vaPage.number-1 > 1 ? vaPage.number-1 : 1}"
									end="${ vaPage.totalPages < 5 ? vaPage.totalPages: vaPage.number+2 <= 2  ? vaPage.number+5 : vaPage.number+2 <= 3  ? vaPage.number+4 : vaPage.number+3 > vaPage.totalPages ? vaPage.totalPages : vaPage.number+3}">
									<c:choose>
										<c:when test="${pageNum != (vaPage.number+1)}">
											<c:if test="${useState == null }">
												<a class="get-started-circle"
													href="${contextRoot}/vote/voteFrontPage_TLK?page=${pageNum}">${pageNum}</a>
											</c:if>
											<c:if test="${useState != null }">
												<a class="get-started-circle"
													href="${contextRoot}/vote/voteFrontPage_TLK?page=${pageNum}&useState=${useState}">${pageNum}</a>
											</c:if>
										</c:when>
										<c:otherwise>
											<button type="button" class="get-started-circle-choose">${pageNum}</button>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<a class="get-started-circle"
									href="${contextRoot}/vote/voteFrontPage_TLK?page=${vaPage.totalPages}">&gt;</a>
							</div>
						</div>
					</div>

					<div>
						<c:forEach var="vote" items="${vaPage.content}" varStatus="status">
							<div class="container border border-dark border-1 rounded-5 my-3">
								<div class="row">
									<!-- 投票內容 -->
									<div class="col-8">
										<h3 class="m-3 ms-5 ">${vote.title}</h3>
										<p class="mx-3 ms-5  mb-4"><span>
												<fmt:formatDate value="${vote.startDate }" pattern="yyyy-MM-dd" />
											</span>~<span>
												<fmt:formatDate value="${vote.endDate }" pattern="yyyy-MM-dd" />
											</span></p>
									</div>
									<div class="offset-1 col-3 my-auto">
										<!-- Button trigger modal -->
										<c:choose>
											<c:when test="${goVote[status.index]}">
												<button class="get-started-btn2" data-bs-toggle="modal"
													data-bs-target="#staticBackdrop${vote.id}">去投票2</button>
											</c:when>
											<c:otherwise>
												<button class="get-started-btn-yello-2" data-bs-toggle="modal"
													data-bs-target="#staticBackdrop${vote.id}">看結果</button>
											</c:otherwise>
										</c:choose>
										<!-- Button trigger modal -->
									</div>
								</div>
							</div>
							<!-- Modal -->
							<c:choose>
								<c:when test="${goVote[status.index]}">
									<div class="modal fade" id="staticBackdrop${vote.id}" data-bs-backdrop="static"
										data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
										aria-hidden="true">
										<div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
											<div class="modal-content">
												<form action="${contextRoot}/vote/addVoteRecord" method="post">
													<div class="modal-body">
														<div class="container">
															<div class="row">
																<div class="offset-2 col-8">
																	<h4>標題</h4>
																	<h4 class="mb-5">${vote.title}</h4>
																	<h4>內容</h4>
																	<p class="mb-5">${vote.context}</p>
																	<h4>截止時間<p class="mb-5">
																			<fmt:formatDate value="${vote.endDate }"
																				pattern="yyyy-MM-dd" />
																		</p>
																	</h4>

																	<input type="hidden" name="voteid"
																		value="${vote.id}" />
																	<input type="hidden" name="userid"
																		value="${sessionScope.pid}" />
																	<div class="form-check">
																		<input class="form-check-input" type="radio"
																			name="choose"
																			id="flexRadioDefault${vote.id}-1" value="1">
																		<label class="form-check-label"
																			for="flexRadioDefault${vote.id}-1">
																			<h4>${vote.select1}</h4>
																		</label>
																	</div>
																	<div class="form-check">
																		<input class="form-check-input" type="radio"
																			name="choose"
																			id="flexRadioDefault${vote.id}-2" value="2">
																		<label class="form-check-label"
																			for="flexRadioDefault${vote.id}-2">
																			<h4>${vote.select2}</h4>
																		</label>
																	</div>
																	<c:if test="${vote.select3 != null}">
																		<div class="form-check">
																			<input class="form-check-input" type="radio"
																				name="choose"
																				id="flexRadioDefault${vote.id}-3"
																				value="3">
																			<label class="form-check-label"
																				for="flexRadioDefault${vote.id}-3">
																				<h4>${vote.select3}</h4>
																			</label>
																		</div>
																	</c:if>
																	<c:if test="${vote.select4 != null}">
																		<div class="form-check">
																			<input class="form-check-input" type="radio"
																				name="choose"
																				id="flexRadioDefault${vote.id}-4"
																				value="4">
																			<label class="form-check-label"
																				for="flexRadioDefault${vote.id}-4">
																				<h4>${vote.select4}</h4>
																			</label>
																		</div>
																	</c:if>
																	<c:if test="${vote.select5 != null}">
																		<div class="form-check">
																			<input class="form-check-input" type="radio"
																				name="choose"
																				id="flexRadioDefault${vote.id}-5"
																				value="5">
																			<label class="form-check-label"
																				for="flexRadioDefault${vote.id}-5">
																				<h4>${vote.select5}</h4>
																			</label>
																		</div>
																	</c:if>

																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">關閉</button>
														<button type="submit" class="btn btn-primary">提交</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="modal fade" id="staticBackdrop${vote.id}" data-bs-backdrop="static"
										data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
										aria-hidden="true">
										<div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
											<div class="modal-content">
												<form action="${contextRoot}/vote/addVoteRecord" method="post">
													<input type="hidden" name="voteid" value="${vote.id}" />
													<input type="hidden" name="userid" value="${sessionScope.pid}" />
													<div class="modal-body">
														<div class="container">
															<div class="row">
																<div class="offset-2 col-8">
																	<h4>標題</h4>
																	<h4 class="mb-5">${vote.title}</h4>
																	<h4>內容</h4>
																	<p class="mb-5">${vote.context}</p>
																	<h4>截止時間<p class="mb-5">
																			<fmt:formatDate value="${vote.endDate }"
																				pattern="yyyy-MM-dd" />
																		</p>
																	</h4>


																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">關閉</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:otherwise>

							</c:choose>
							<!-- Modal -->
						</c:forEach>





	</div>
						<!-- 原本 -->
</div>

					<!-- ---------------------------程式區-------------------------------- -->


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