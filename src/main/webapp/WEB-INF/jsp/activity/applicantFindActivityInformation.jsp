<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<meta content="width=device-width, initial-scale=1.0" name="viewport">
				<title>活動瀏覽</title>
				<link rel="stylesheet" href="${contextRoot}/css/space.css">
				<style>
				</style>
			</head>

			<body>
				<jsp:include page="../layout/navbar.jsp" />

				<main id="main">
					<!-- ======= Breadcrumbs ======= -->
					<div class="breadcrumbs">
						<div class="container">
							<h2>活動瀏覽</h2>
							<p>定期參與活動有助於身心健康
							</p>
						</div>
					</div>
					<!-- ======= Breadcrumbs ======= -->


					<div class="container">
						<div class="row my-5">
							<div class="col-lg-3 col-sm-8">
								<p>發佈時間:&nbsp
									<fmt:formatDate value="${activityInformation.applicantCreateDate}"
										pattern="yyyy-MM-dd HH:mm" />
								</p>
							</div>
							<div class="col-lg-1 col-sm-3">
								<i class="fa-solid fa-eye"></i>${activityInformation.count }
							</div>
						</div>
					</div>

					<!-- 線 -->
					<div class="container">
						<div class="row">
							<div class="progress mb-5 " style="height: 2px;">
								<div class="progress-bar" role="progressbar" aria-label="Example 1px high"
									style="width: 10%; background-color: #5fcf80;" aria-valuenow="25" aria-valuemin="0"
									aria-valuemax="100">
								</div>
							</div>
						</div>
					</div>
					<!-- 線 -->

					<section id="events" class="events">
						<div class="container" data-aos="fade-up">
							<div class="row">
								<div class="offset-lg-4 col-lg-4 col-sm-12">
									<div class="card">
										<div class="card-img">
											<img src="${contextRoot}/activity/activityPicture?activityId=${activityInformation.p_Id}"
												onerror="this.src='${contextRoot}/download/未上傳圖片.png'"
												class="w-100 announcementInformation-img" alt="...">
										</div>
										<div class="card-body">
											<h5 class="card-title"><a href="">${activityInformation.title}</a></h5>
											<p class="fst-italic text-center">報名截止日:
												<fmt:formatDate value="${activityInformation.applicantDeadDate}"
													pattern="yyyy-MM-dd HH:mm" />
											</p>
											<p class="card-text">活動說明:${activityInformation.text}</p>
											<p class="card-text">講師名稱:${activityInformation.activityTeacher}</p>
											<p class="card-text">地點:${activityInformation.place}</p>
											<p class="card-text">講師聯絡方式:${activityInformation.teacherPhone}</p>
											<h4 class="text-end"><i
													class="bx bx-user"></i>&nbsp;${activityInformation.applicantNumber}/${activityInformation.applicantNumberFull}
												&nbsp; &nbsp; &nbsp; ${activityInformation.applicantStatus}
											</h4>
											<c:if test="${activityInformation.applicantStatus == '未額滿'}">
												<form method="post"
													action="${contextRoot}/activity/applicantAddActivity">
													<input type="hidden" name="activityId"
														value="${activityInformation.p_Id}">
													<button type="submit" class="btn btn-outline-success">報名</button>
												</form>
											</c:if>
										</div>
									</div>
								</div>

							</div>

						</div>
					</section><!-- End Events Section -->
				</main>


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


				<!-- <div class="container ">
				<div class="title-css">
					<img src="${contextRoot}/activity/activityPicture?activityId=${activityInformation.p_Id}"
						style=" width:480px">
					<p>活動名:${activityInformation.title}</p>
					<p>主旨:${activityInformation.text}</p>
					<p>講師名稱:${activityInformation.activityTeacher}</p>
					<p>地點:${activityInformation.place}</p>
					<p>講師聯絡方式:${activityInformation.teacherPhone}</p>
					<p>報名人數:${activityInformation.applicantNumber}/${activityInformation.applicantNumberFull}</p>
					<p>報名狀態:${activityInformation.applicantStatus}</p>
					<P>報名截止日:${activityInformation.applicantDeadDate}</P>
				</div>
				<form method="post" action="${contextRoot}/activity/applicantAddActivity">
					<input type="hidden" name="activityId" value="${activityInformation.p_Id}">
					<button type="submit" class="btn btn-outline-primary">報名</button>
				</form>
			</div> -->

			</body>

			</html>