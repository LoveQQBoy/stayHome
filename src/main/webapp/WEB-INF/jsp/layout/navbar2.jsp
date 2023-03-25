<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
		<!DOCTYPE html>
		<html>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

		<head>

			<meta charset="UTF-8">

		</head>

		<body>

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">
					<a class="navbar-brand" href="${contextRoot}/">Navbar</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
						data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
						<ul class="navbar-nav">

							<!-- 聖富/柏杰 -->

							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#"
									id=household " role=" button" data-bs-toggle="dropdown" aria-expanded="false">
									住戶系統 </a>
								<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="household">
									<li><a class="dropdown-item" href="${contextRoot}/vote/votePage">投票</a></li>
									<li><a class="dropdown-item" href="${contextRoot}/household/readpage">住戶資料修改</a>
									</li>
									<li><a class="dropdown-item" href="#">新增</a></li>
								</ul>
							</li>

							<!-- 聖富/柏杰 -->
							<!-- 翊綸 -->

							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="shopMall"
									role="button" data-bs-toggle="dropdown" aria-expanded="false">
									商城 </a>
								<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="shopMall">
									<li><a class="dropdown-item" href="${contextRoot}/collectParcel/userDemo">代收包裹</a></li>
									<li><a class="dropdown-item" href="#">新增</a></li>
									<li><a class="dropdown-item" href="#">新增</a></li>
								</ul>
							</li>

							<!-- 翊綸 -->
							<!-- 右昕 -->

							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="postulate"
									role="button" data-bs-toggle="dropdown" aria-expanded="false">
									公設 </a>
								<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="postulate">
									<li><a class="dropdown-item" href="${contextRoot}/repair/residentList">維修回報</a></li>
									<li><a class="dropdown-item" href="#">新增</a></li>
									<li><a class="dropdown-item" href="#">新增</a></li>
								</ul>
							</li>

							<!-- 右昕 -->

							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="Activity"
									role="button" data-bs-toggle="dropdown" aria-expanded="false">
									公告 </a>
								<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="Activity">
									<li><a class="dropdown-item"
											href="${contextRoot}/announcement/announcementRead">公告瀏覽</a></li>
									<li><a class="dropdown-item"
											href="${contextRoot}/activity/applicantFindActivity">活動瀏覽</a></li>
									<li><a class="dropdown-item"
											href="${contextRoot}/activity/applicantPersonal">個人報名資訊</a></li>
									<li><a class="dropdown-item" href="#">新增</a></li>
								</ul>
							</li>
							<!-- 登入 -->
							<c:choose>
								<c:when test="${ name != null }">
									<li><a href="#" class="btn btn-secondary me-2">住戶:${name}</a></li>
									<li><input type="button" value="登出" class="btn btn-outline-danger"
											onclick="javascrtpt:window.location.href='${contextRoot}/household/logout'" />
									</li>
									&ensp;
									<li><a class="btn btn-outline-dark me-2" href="${contextRoot}/manage">後台</a></li>
								</c:when>
								<c:otherwise>
									<input type="button" value="登入" class="btn btn-outline-primary me-2"
										onclick="javascrtpt:window.location.href='${contextRoot}/household/loginpage'" />
									<input type="button" value="註冊" class="btn btn-primary"
										onclick="javascrtpt:window.location.href='${contextRoot}/household/add'" />
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</nav>




		</body>

		</html>