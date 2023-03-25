<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
				<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
					<script type="text/javascript" src="${contextRoot}/js/jquery-3.6.3.min.js"></script>

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
					<div class="container">
						<!-- ---------------------------程式區-------------------------------- -->
						<!-- 新增 -->
						<div class="row">
							<div class="offset-1 col-6">
								<!-- 新增 -->
								<h6 class="mt-3">維修編號 : ${repair.id}</h6>
								<h6>時間 :
									<fmt:formatDate value="${repair.date}" pattern="yyyy-MM-dd ,a hh:mm:ss" />
								</h6>
								<h6>回覆 :
									<c:if test="${repair.replyState == '1'}">
										<i class="fa fa-check-square"
											style="position: relative;top:2px;font-size:20px;color:green"></i>
									</c:if>
									<c:if test="${repair.replyState == '0'}">
										<i class="material-icons"
											style="position: relative;top:4px;font-size:20px;color:red">cancel</i>
									</c:if>
								</h6>

								<br />

								<div class="mb-3 mt-3">
									<h6 class="fw-bold">留言 :</h6>
									<p>${repair.description}</p>
								</div>



								<c:choose>
									<c:when test="${repair.replyState == '1'}">
										<div class="mb-3">
											<h6 class="fw-bold">回覆時間 :
												<fmt:formatDate value="${repair.replyTime}"
													pattern="yyyy-MM-dd ,a hh:mm:ss" />
											</h6>
											<h6 class="fw-bold">回覆 :</h6>
											<p>${repair.reply}</p>
										</div>
									</c:when>

									<c:otherwise>
									</c:otherwise>
								</c:choose>

								<button class="btn btn-primary" type="button" onclick="history.back()">返回</button>
							</div>
							<!-- ---------------------------程式區-------------------------------- -->
							<!-- 新增 -->
							<div class="mb-3 col-4">
								<h6 class="fw-bold">照片 :</h6>
								<img style="height:350px" src="<c:url value='/repairPicture/${repair.id}'/>" />
							</div>

						</div>
						<!-- 新增 -->
					</div>


				</body>

				</html>