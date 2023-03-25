<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>報名資訊</title>

			</head>

			<body>

				<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>

				<!-- 右邊 -->
				<div class="container-right">
					<div class="container">
						<div class="row">
							<div class="mt-5 header-border ">
								<h1>活動瀏覽</h1>
							</div>
							<div class="container">
								<!-- 活動新增區塊 -->
								<div class="container-fluid">
									<div class="row mt-4">
										<div>
											<form action="${contextRoot}/activity/activityFindPageByTimeAndString"
												method="post">
												<label class="" for="keyword">關鍵字查詢
													<input type="text" class="" name="keyWord" id="keyword">
												</label>&nbsp; &nbsp;
												<label for="startTime">時間查詢
													<input type="date" name="startTime" id="startTime">~
													<input type="date" name="endTime" id="endTime">
												</label>&nbsp; &nbsp;
												狀態查詢
												<label for="activityStatus1">未額滿
													<input type="radio" name="activityStatus" id="activityStatus1"
														value="未額滿">
												</label>
												<label for="activityStatus2">已額滿
													<input type="radio" name="activityStatus" id="activityStatus2"
														value="已額滿">
												</label>
												<label for="activityStatus3">已截止
													<input type="radio" name="activityStatus" id="activityStatus3"
														value="已截止">
												</label>&nbsp; &nbsp;
												<button type="submit" class="btn btn-outline-success">查詢</button>
											</form>
											<a class="offset-9 col-1 btn btn-primary mt-3"
												href="${contextRoot}/activity/addActivityPage">活動新增</a>
										</div>
									</div>
								</div>
								<!-- 活動新增區塊 -->
								<!-- 頁數 -->
								<figure class="text-center">
									<blockquote class="blockquote">
										<c:choose>
											<c:when test="${searchPage != null}">
												<!-- 條件搜尋 -->
												<c:forEach var="pageNumber" begin="1" end="${searchPage.totalPages}">
													<c:choose>
														<c:when test="${pageNumber != searchPage.number+1}">
															<form action="${contextRoot}/activity/activityFindPage"
																method="get" style="display: inline ;">
																<input type="hidden" name="keyWord" value="${keyWord}">
																<input type="hidden" name="startTime"
																	value="${startTime}">
																<input type="hidden" name="endTime" value="${endTime}">
																<input type="hidden" name="searchPageNumber"
																	value="${pageNumber}">
																<input type="hidden" name="activityStatusString"
																	value="${activityStatusString}">
																<button type="submit" class="btn btn-outline-success"
																	style="display: inline ;">${pageNumber}</button>
															</form>
														</c:when>
														<c:otherwise>
															<div class="btn btn-success" style="display: inline ;">
																${pageNumber}</div>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<!-- 條件搜尋 -->
											</c:when>
											<c:otherwise>
												<!-- 沒有條件搜尋執行這 -->
												<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
													<c:choose>
														<c:when test="${pageNumber != page.number+1}">
															<a class="btn btn-outline-success"
																href="${contextRoot}/activity/activityFindPage?pageNumber=${pageNumber}">${pageNumber}</a>
														</c:when>
														<c:otherwise>
															<div class="btn btn-success">${pageNumber}</div>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<!-- 沒有條件搜尋執行這 -->
											</c:otherwise>
										</c:choose>
									</blockquote>
								</figure>
							</div>
							<!-- 頁數 -->
							<!-- 內容 -->
							<div class="ms-5">
								<table class="table">
									<thead>
										<tr>
											<th>活動名</th>
											<th>報名人數</th>
											<th>截止時間</th>
											<th>詳情</th>
											<th>動作</th>
										</tr>
									</thead>
									<tbody>
										<!-- 條件搜尋 -->
										<c:choose>
											<c:when test="${searchPage != null}">
												<c:forEach var="page" items="${searchPage.content}">
													<tr>
														<td>${page.title}</td>
														<td>${page.applicantNumber}/${page.applicantNumberFull}</td>
														<td>
															<fmt:formatDate value="${page.applicantDeadDate}"
																pattern="yyyy-MM-dd HH:mm" />
														</td>
														<td><a
																href="${contextRoot}/activity/Information?activityId=${page.p_Id}">詳情</a>
														</td>
														<td><a
																href="${contextRoot}/activity/update?id=${page.p_Id}">修改</a>
															<form action="${contextRoot}/activity/activityDelete"
																method="post">
																<input type="hidden" name="_method" value="delete" />
																<input type="hidden" name="id" value="${page.p_Id}" />
																<input type="submit"
																	class="btn btn-outline-danger btn-sm" value="刪除">
															</form>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="page" items="${page.content}">
													<tr>
														<td>${page.title}</td>
														<td>${page.applicantNumber}/${page.applicantNumberFull}</td>
														<td>
															<fmt:formatDate value="${page.applicantDeadDate}"
																pattern="yyyy-MM-dd HH:mm" />
														</td>
														<td><a
																href="${contextRoot}/activity/Information?activityId=${page.p_Id}">詳情</a>
														</td>
														<td><a
																href="${contextRoot}/activity/update?id=${page.p_Id}">修改</a>
															<form action="${contextRoot}/activity/activityDelete"
																method="post">
																<input type="hidden" name="_method" value="delete" />
																<input type="hidden" name="id" value="${page.p_Id}" />
																<input type="submit"
																	class="btn btn-outline-danger btn-sm" value="刪除">
															</form>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										<!-- 條件搜尋 -->
									</tbody>
								</table>
								<!-- 內容 -->
							</div>
						</div>
					</div>
				</div>

				<script>
					let startDate = document.getElementById("startTime");
					let endDate = document.getElementById("endTime");
					startDate.addEventListener('change', function () {
						console.log(endDate.value)
						//startDate>endDate
						if (moment(startDate.value).isAfter(endDate.value)) {
							Swal.fire({
								icon: 'warning',
								title: '開始時間不能超過結束時間',
								showConfirmButton: false,
								timer: 2000
							})
						}
					})

					endDate.addEventListener('change', function () {
						if (moment(endDate.value).isBefore(startDate.value)) {
							Swal.fire({
								icon: 'warning',
								title: '結束時間不能小於開始時間',
								showConfirmButton: false,
								timer: 2000
							})

						}
					})
				</script>
				<!-- sweetAlert -->
				<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

			</body>

			</html>