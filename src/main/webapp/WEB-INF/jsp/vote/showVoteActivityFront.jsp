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

				<div class="container">
					<!-- ---------------------------程式區-------------------------------- -->

					<div class="mt-5">
						<form action="${contextRoot}/vote/voteFrontPage" method="get">
							<table class="table table-bordered align-middle text-center">
								<tbody>
									<tr>
										<th>狀態</th>
										<th>
											<input type="radio" name="useState" id="using" value="1" />
											<label for="using">投票中</label>
											<input type="radio" name="useState" id="used" value="2" />
											<label for="used">已結束</label>
										</th>
										<th>
											<button type="submit" class="btn btn-primary">查詢</button>
											<button type="reset" class="btn btn-primary">重設</button>
										</th>
									</tr>
								</tbody>
							</table>
						</form>
					</div>

					<div>
						<table class="table table-bordered align-middle text-center">
							<thead class="table-secondary">
								<tr>
									<th scope="col">標題</th>
									<th scope="col">投票時間</th>
									<th scope="col">狀態</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="vote" items="${vaPage.content}" varStatus="status">
									<tr>
										<td>${vote.title}</td>

										<td><span>
												<fmt:formatDate value="${vote.startDate }" pattern="yyyy-MM-dd" />
											</span>~<span>
												<fmt:formatDate value="${vote.endDate }" pattern="yyyy-MM-dd" />
											</span></td>

										<td>
											<c:set var="nowDate">
												<fmt:formatDate value="<%=new java.util.Date() %>"
													pattern="yyyy-MM-dd" />
											</c:set>
											<c:set var="sDate">
												<fmt:formatDate value="${vote.startDate}" pattern="yyyy-MM-dd" />
											</c:set>
											<c:set var="eDate">
												<fmt:formatDate value="${vote.endDate}" pattern="yyyy-MM-dd" />
											</c:set>
											<c:choose>
												<c:when test="${nowDate > eDate}">已結束 </c:when>
												<c:otherwise>投票中</c:otherwise>
											</c:choose>
										</td>
										<td>
											<div class="d-inline-flex">
												<c:choose>
													<c:when test="${goVote[status.index]}">
														<form action="${contextRoot}/vote/doVotePage" method="get">
															<input type="hidden" name="voteid" value="${vote.id}">
															<button type="submit" class="btn btn-primary">投票</button>
														</form>
													</c:when>
													<c:otherwise>
														<form action="${contextRoot}/vote/voteDetailPage" method="get">
															<input type="hidden" name="voteid" value="${vote.id}">
															<button type="submit"
																class="btn btn-outline-primary">看結果</button>
														</form>
													</c:otherwise>
												</c:choose>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<c:forEach var="pageNum" begin="1" end="${vaPage.totalPages}">
							<c:choose>
								<c:when test="${pageNum != (vaPage.number+1)}">
									<c:if test="${useState == null }">
										<a class="btn btn-outline-success"
											href="${contextRoot}/vote/voteFrontPage?page=${pageNum}">${pageNum}</a>
									</c:if>
									<c:if test="${useState != null }">
										<a class="btn btn-outline-success"
											href="${contextRoot}/vote/voteFrontPage?page=${pageNum}&useState=${useState}">${pageNum}</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-success">${pageNum}</button>
								</c:otherwise>
							</c:choose>
						</c:forEach>
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