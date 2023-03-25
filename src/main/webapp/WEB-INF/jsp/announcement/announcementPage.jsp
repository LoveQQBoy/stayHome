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
								<h1>公告瀏覽</h1>
							</div>
							<div class="container">
								<!-- 活動新增區塊 -->
								<div class="container-fluid">
									<div class="row">
										<a class="offset-9 col-1 get-started-btn-square mt-3"
											href="${contextRoot}/announcement/announcementAdd">公告新增</a>
									</div>
								</div>
								<!-- 活動新增區塊 -->
								<!-- 頁數 -->
								<figure class="text-center">
									<blockquote class="blockquote">
										<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
											<c:choose>
												<c:when test="${pageNumber != page.number+1}">
													<a class="btn btn-outline-success"
														href="${contextRoot}/announcement/announcement?pageNumber=${pageNumber}">${pageNumber}</a>
												</c:when>
												<c:otherwise>
													<div class="btn btn-success">${pageNumber}</div>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</blockquote>
								</figure>
							</div>
							<!-- 頁數 -->
							<div class="ms-5">
								<table class="table">
									<thead>
										<tr>
											<th>公告名</th>
											<th>公告時間</th>
											<th>詳情</th>
											<th>動作</th>
										</tr>
									</thead>
									<tbody id="tbody">
										<c:forEach var="page" items="${page.content}">
											<tr>
												<td>${page.title}</td>
												<td>
													<fmt:formatDate value="${page.announcementDate}"
														pattern="yyyy-MM-dd HH:mm" />
												</td>
												<td><a
														href="${contextRoot}/announcement/announcement/${page.p_Id}">詳情</a>
												</td>
												<td><a
														href="${contextRoot}/announcement/announcemetUpdate?announcementId=${page.p_Id}">修改公告</a>
													<form action="${contextRoot}/announcement/announcement/${page.p_Id}"
														method="post">
														<input type="hidden" name="_method" value="DELETE" />
														<input type="submit" class="btn btn-outline-danger btn-sm"
															value="刪除">
													</form>

													<button class="btn btn-success" id="promote"
														value="${page.p_Id}">推廣</button>

												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- end -->
							</div>
						</div>
					</div>
				</div>
			</body>

			<script>
				let promotetBody = document.getElementById("tbody")
				let promoteButton = promotetBody.querySelectorAll("button")

				for (let i = 0; i < promoteButton.length; i++) {
					promoteButton[i].addEventListener("click", function (event) {
						let announcementId = event.target.value
						$.ajax({
							method: 'get',
							url: '${contextRoot}/announcement/sendEmail',
							data: { announcementId: announcementId },
							success: function (success) {

							}, error: function (jqXHR, textStatus, errorThrow) {
								console.log("Error:", textStatus, errorThrow)
							}
						})
					})

				}


			</script>

			</html>