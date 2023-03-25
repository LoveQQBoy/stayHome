<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
			<!DOCTYPE html>
			<html>

			<head>
				<!-- Favicons -->
				<link href="${contextRoot}/assets/img/favicon.png" rel="icon">
				<link href="${contextRoot}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

				<!-- Google Fonts -->
				<link
					href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
					rel="stylesheet">

				<!-- Vendor CSS Files -->
				<link href="${contextRoot}/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
				<link href="${contextRoot}/assets/vendor/aos/aos.css" rel="stylesheet">
				<link href="${contextRoot}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
				<link href="${contextRoot}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
				<link href="${contextRoot}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
				<link href="${contextRoot}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
				<link href="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

				<!-- Template Main CSS File -->
				<link href="${contextRoot}/assets/css/style.css" rel="stylesheet">

				<!-- fontAwsome -->
				<link rel="stylesheet"
					href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
				  <!-- moment.js -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>

			</head>

			<body>
				
				<!-- ======= Header ======= -->
				<header id="header" class="fixed-top">
					<div class="container d-flex align-items-center">

						<h1 class="logo me-auto"><a href="${contextRoot}/">宅在家</a></h1>
						<!-- Uncomment below if you prefer to use an image logo -->
						<!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

						<nav id="navbar" class="navbar order-last order-lg-0">
							<ul>
								<!-- 確認是否有登入 -->
								<c:if test="${name != null}">
									<li><a class="" href="${contextRoot}/">首頁</a></li>
									<li class="dropdown"><a href="#"><span>公告系統</span> <i
												class="bi bi-chevron-down"></i></a>
										<ul>
											<li><a href="${contextRoot}/announcement/announcementRead">社區公告</a></li>
											<li><a href="${contextRoot}/activity/applicantFindActivity">社區活動</a></li>
											<li><a href="${contextRoot}/activity/applicantPersonal">活動報名查詢</a></li>
										</ul>
									</li>
									<li class="dropdown"><a href="#"><span>住戶系統</span> <i
												class="bi bi-chevron-down"></i></a>
										<ul>
											<li><a href="${contextRoot}/feedback/showfeedback">意見留言板</a></li>
											<li><a href="${contextRoot}/vote/voteFrontPage">投票系統</a></li>
											<li><a href="${contextRoot}/vote/voteFrontPage_TLK">投票系統_LTK</a></li>
											<li><a href="${contextRoot}/manageCost/manageCostPageFront">管理費</a></li>
										</ul>
									</li>
									<li class="dropdown"><a href="#"><span>服務系統</span> <i
												class="bi bi-chevron-down"></i></a>
										<ul>
											<li><a href="${contextRoot}/collectParcel/userDemo">包裹代收</a></li>
										</ul>
									</li>
									<li class="dropdown"><a href="#"><span>公設</span> <i
												class="bi bi-chevron-down"></i></a>
										<ul>
											<li><a href="${contextRoot}/public/appointmentList">預約公設</a></li>
											<li><a href="${contextRoot}/repair/residentList">維修回報</a></li>
										</ul>
									</li>
								</c:if>
							</ul>
							<i class="bi bi-list mobile-nav-toggle"></i>
						</nav>
						<!-- .navbar -->
						<!-- 登入 -->
						<c:choose>
							<c:when test="${ name != null }">
								<a href="${contextRoot}/household/editFront?id=${pid}" class="get-started-btn">住戶:${name}</a>
								<a href="${contextRoot}/household/logout" class="get-started-btn">登出</a>
								<c:if test="${managerPermission > 0}">
								<a class="get-started-btn" href="${contextRoot}/manage">後台</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<a class="get-started-btn" href="${contextRoot}/household/loginpage">登入</a>
								<a class="get-started-btn" href="${contextRoot}/household/add">註冊</a>
							</c:otherwise>
						</c:choose>
						<!-- 登入 -->
					</div>
				</header>
				<!-- End Header -->


			</body>

			</html>