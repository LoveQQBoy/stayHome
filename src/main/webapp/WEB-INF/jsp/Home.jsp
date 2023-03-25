<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<meta charset="utf-8">
				<meta content="width=device-width, initial-scale=1.0" name="viewport">

				<title>宅在家</title>
				<meta content="" name="description">
				<meta content="" name="keywords">


				<!-- =======================================================
  * Template Name: Mentor - v4.10.0
  * Template URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

			</head>

			<body>

				<jsp:include page="layout/navbar.jsp"></jsp:include>

				<!-- ======= Hero Section ======= -->
				<section id="hero" class="d-flex justify-content-center align-items-center">
					<div class="container position-relative" data-aos="zoom-in" data-aos-delay="100">
						<h1>
							早安<br>活動是一天的開始
						</h1>
						<h2>參與社區活動有助身心健康</h2>
						<a href="${contextRoot}/activity/applicantFindActivity" class="btn-get-started">瀏覽活動</a>
					</div>
				</section>
				<!-- End Hero -->

				<main id="main">

					<!-- ======= About Section ======= -->
					<section id="about" class="about">
						<div class="container" data-aos="fade-up">
							<div class="row d-flex justify-content-center align-items-center ">
								<div class="col-lg-6 order-1 order-lg-2 content" data-aos="fade-left"
									data-aos-delay="100">
									<h3 class="text-center">最新投票</h3>
									<div class="card mb-3">
										<div class="row g-0">
											<div class="col-md-4">
												<img src="${contextRoot}/download/投票.jpg" class="w-100 my-card-image"
													style="max-height: 220px; object-fit: cover" alt="">
											</div>
											<div class="col-md-8">
												<div class="container">
													<div class="row d-flex ">
														<div class="card-body pb-0">
															<h5 class="card-title">${pageVoteActivity.content[0].title}
															</h5>
															<p class="card-text ms-3 m-0 hiddenText"
																style="min-height: 80px;width:90%;	">
																${pageVoteActivity.content[0].context}
															</p>
															<p class="card-text"></p>
														</div>
														<div class="text-end mt-4 pt-0 mb-1">
															<span class="ms-3"> <small
																	class="text-muted">${pageVoteActivity.content[0].startDate}</small>
															</span> <a href="${contextRoot}/vote/voteFrontPage"
																class="get-started-btn-square ms-5">詳情</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
								<div class="col-lg-5 pt-4 pt-lg-0 content">
									<h3 class="text-center">最新公告</h3>
									<table class="table text-center">
										<thead>
											<tr>
												<th scope="col">公告名</th>
												<th scope="col">發布日期</th>
												<th scope="col">詳情</th>
											</tr>
										</thead>
										<c:forEach var="page" items="${pageAnnouncement.content}" begin="0" end="2">
											<tbody>
												<tr>
													<td>${page.title}</td>
													<td>
														<fmt:formatDate value="${page.announcementDate}"
															pattern="yyyy-MM-dd HH:mm" />
													</td>
													<td><a class="get-started-btn-square" style="margin-left: 0px;"
															href="${contextRoot}/announcement/announcementRead/${page.p_Id}">詳情
														</a></td>
												</tr>
											</tbody>
										</c:forEach>
									</table>

								</div>
							</div>

						</div>
					</section>
					<!-- End About Section -->

					<!-- ======= Counts Section ======= -->
					<section id="counts" class="counts section-bg">
						<div class="container">

							<div class="row counters">

								<div class="col-lg-3 col-6 text-center">
									<span data-purecounter-start="0" data-purecounter-end="${announcementNumber}"
										data-purecounter-duration="1" class="purecounter"></span>
									<p>社區公告</p>
								</div>

								<div class="col-lg-3 col-6 text-center">
									<span data-purecounter-start="0" data-purecounter-end="${activityNumber}"
										data-purecounter-duration="1" class="purecounter"></span>
									<p>社區活動</p>
								</div>

								<div class="col-lg-3 col-6 text-center">
									<span data-purecounter-start="0" data-purecounter-end="${pageVoteNumber }"
										data-purecounter-duration="1" class="purecounter"></span>
									<p>投票活動</p>
								</div>

								<div class="col-lg-3 col-6 text-center">
									<span data-purecounter-start="0" data-purecounter-end="${totalResponse}"
										data-purecounter-duration="1" class="purecounter"></span>
									<p>意見反應回饋數</p>
								</div>

							</div>

						</div>
					</section>
					<!-- End Counts Section -->

					<!-- ======= Why Us Section ======= -->
					<section id="why-us" class="why-us">
						<div class="container" data-aos="fade-up">

							<div class="row">
								<div class="col-lg-4 d-flex align-items-stretch">
									<div class="content">
										<h3>快來參與社區活動</h3>
										<p>這個社區需要大家一起共同打造美好的環境</p>
									</div>
								</div>
								<div class="col-lg-8 d-flex align-items-stretch" data-aos="zoom-in"
									data-aos-delay="100">
									<div class="icon-boxes d-flex flex-column justify-content-center">
										<div class="row">
											<div class="col-xl-4 d-flex align-items-stretch">
												<a href="#">
													<div class="icon-box mt-4 mt-xl-0">
														<i class="bx ri-price-tag-2-line"></i>
														<h4>意見反應</h4>
														<p>隔壁的鄰居又再吵人了嗎?歡迎寄信我們會盡快為您處裡</p>
													</div>
												</a>
											</div>
											<div class="col-xl-4 d-flex align-items-stretch">
												<a href="#">
													<div class="icon-box mt-4 mt-xl-0">
														<i class="bx ri-fingerprint-line"></i>
														<h4>維修回報</h4>
														<p>社區的公設又壞了嗎?趕快來幫忙回報吧!</p>
													</div>
												</a>
											</div>
											<div class="col-xl-4 d-flex align-items-stretch">
												<a href="#">
													<div class="icon-box mt-4 mt-xl-0">
														<i class=" bx ri-calendar-todo-line"></i>
														<h4>包裹代收</h4>
														<p>想知道你的包裹是否到了嗎?歡迎點選</p>
													</div>
												</a>
											</div>
										</div>
									</div>
									<!-- End .content-->
								</div>
							</div>

						</div>
					</section>
					<!-- End Why Us Section -->

					<!-- ======= Popular Courses Section ======= -->
					<section id="popular-courses" class="courses">
						<div class="container" data-aos="fade-up">
							<div class="section-title">
								<h2>活動</h2>
								<p>熱門活動</p>
							</div>
							<div class="row" data-aos="zoom-in" data-aos-delay="100">
								<c:forEach items="${pageActivity.content}" var="page" begin="0" end="2">
									<div class="col-lg-4  col-md-6 px-5">
										<div class="course-item">
											<img src="${contextRoot}/activity/activityPicture?activityId=${page.p_Id}"
												class="w-100" style="object-fit: cover; height: 350px; width: 350px;">
											<div class="course-content">
												<div class="d-flex justify-content-between align-items-center mb-3">
													<h4>
														<i
															class="bx bx-user"></i>&nbsp;${page.applicantNumber}/${page.applicantNumberFull}
													</h4>
												</div>
												<h3>
													<a
														href="${contextRoot}/activity/applicantFindActivityInformation?activityId=${page.p_Id}">活動名:${page.title}</a>
												</h3>
												<p>活動狀態:${page.applicantStatus}</p>
												<div class="trainer d-flex justify-content-between align-items-center">
													<div class="trainer-profile d-flex align-items-center">
													</div>
													<div class="container-fluid p-0">
														<div class="row">
															<div class="col-9">
																截止時間:
																<fmt:formatDate value="${page.applicantDeadDate}"
																	pattern="yyyy-MM-dd HH:mm" />
															</div>
															<div class="col-3">
																<i class="fa-solid fa-eye"></i>&nbsp;${page.count}
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- End Course Item-->
								</c:forEach>
							</div>

						</div>
					</section>
					<!-- End Popular Courses Section -->
					<!-- ======= Trainers Section ======= -->
					<section id="popular-courses" class="courses">
						<div class="container" data-aos="fade-down">
							<div class="section-title text-center">
								<p>未來氣象預報</p>
								<select id="county">
									<option value="臺北市">臺北市</option>
									<option value="新北市">新北市</option>
									<option value="新竹市">新竹市</option>
									<option value="臺中市">臺中市</option>
									<option value="臺南市">臺南市</option>
									<option value="高雄市" selected>高雄市</option>
								</select>
							</div>
							<div class="row row-cols-8" data-aos="zoom-in-left" data-aos-delay="300"
								data-aos-duration="" id="weather"></div>

						</div>
					</section>
					<!-- End Popular Courses Section -->

				</main>
				<!-- End #main -->


				<script>
					window.onload = function () {
						weather()
						hiddenText()
					}

					//文字隱藏
					function hiddenText() {
						var len = 100; // 超過50個字以"..."取代
						$(".hiddenText").each(function (i) {
							if ($(this).text().length > len) {
								$(this).attr("title", $(this).text());
								var text = $(this).text().substring(0, len - 1) + "...";
								$(this).text(text);
							}
						});
					}

					function weather() {
						fetch("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-089?Authorization=CWB-2ED84936-8B45-46A9-9C7E-D3B12E0C2170", {
							method: "get"
						}).then(response => {
							return response.json()
						}).then(success => {
							/////縣市監聽,當選到這個縣市時就會更改成對應的縣市
							let countyElement = document.getElementById("county")
							countyElement.addEventListener("change", function (event) {
								county = event.target.value
								console.log(county)
								chooseCounty(county)
							})
							/////預設縣市//////
							let countyValue = countyElement.value
							chooseCounty(countyValue)

							////////////天氣處裡區塊/////////////
							function chooseCounty(county) {
								//天氣所需之變數
								let predictTime = []
								let temperature = []
								let temperatureStatus = ''
								let startTime = [];
								let endTime = [];
								let rain = [];
								let rainfall = ''
								let wxTime = [];
								let wxValue = [];
								let wxStatus = ''
								// 判斷縣市
								for (i = 0; i < success.records.locations[0].location.length; i++) {
									// 找出目標縣市
									if (success.records.locations[0].location[i].locationName === county) {
										// 縣市名稱

										// 判斷天氣
										for (y = 0; y < success.records.locations[0].location[i].weatherElement.length; y++) {
											//找出目標天氣狀態降雨機率
											if (success.records.locations[0].location[i].weatherElement[y].description === '12小時降雨機率') {

												let weatherElement = success.records.locations[0].location[i].weatherElement[y];
												//去找降雨機率的時間
												for (z = 0; z < weatherElement.time.length; z++) {

													//將降雨機率和溫度的筆數一致
													for (j = 0; j < 4; j++) {
														startTime.push(weatherElement.time[z].startTime)
														endTime.push(weatherElement.time[z].endTime)
														rain.push(weatherElement.time[z].elementValue[0].value + '%')
													}

												}
												//將降雨機率寫成key value
												rainfall = { 'statrTime': startTime, 'endTime': endTime, 'rain': rain }
											}
											//找出目標天氣狀態溫度
											if (success.records.locations[0].location[i].weatherElement[y].description === '溫度') {
												let weatherElement = success.records.locations[0].location[i].weatherElement[y];
												for (z = 0; z < weatherElement.time.length; z++) {
													predictTime.push(weatherElement.time[z].dataTime)
													temperature.push(weatherElement.time[z].elementValue[0].value)
												}
												//將溫度寫成key value
												temperatureStatus = { 'predictTime': predictTime, 'temperature': temperature }

											}
											//找出天氣現象
											if (success.records.locations[0].location[i].weatherElement[y].description === '天氣現象') {
												let weatherElement = success.records.locations[0].location[i].weatherElement[y];
												for (z = 0; z < weatherElement.time.length; z++) {
													wxTime.push(weatherElement.time[z].startTime)
													wxValue.push(weatherElement.time[z].elementValue[0].value)
												}
												wxStatus = {
													'wxTime': wxTime, 'wxValue': wxValue
												}
											}
										}
									}
								}
								////////////天氣處裡區塊/////////////

								let msg_data = '';
								for (i = 0; i < 8; i++) {
									// console.log('預測時間:' + temperatureStatus.predictTime[i])
									// console.log('攝氏溫度:' + temperatureStatus.temperature[i])
									// console.log('降雨機率:' + rainfall.rain[i])
									msg_data += '<div class="col">'

									//晴天時的天氣圖片
									if (wxStatus.wxValue[i].includes('晴')) {
										msg_data += '<i class="fa-regular fa-sun" style="font-size:60px; margin:0 0 20px 30px;"></i>'
									} else if (wxStatus.wxValue[i].includes('陰')) {
										msg_data += '<i class="fa-solid fa-cloud" style="font-size:60px; margin:0 0 20px 30px;"></i>'
									} else if (wxStatus.wxValue[i].includes('雲')) {
										msg_data += '<i class="fa-brands fa-cloudversify" style="font-size:60px; margin:0 0 20px 30px;"></i>'
									} else if (wxStatus.wxValue[i].includes('雨')) {
										msg_data += '<i class="fa-solid fa-cloud-rain" style="font-size:60px; margin:0 0 20px 30px;"></i>'
									} else {
										msg_data += '<i class="fa-regular fa-face-smile-wink" style="font-size:60px; margin:0 0 20px 30px;"></i>'
									}

									//時間轉換
									let timeFormat = moment(temperatureStatus.predictTime[i]).format('MM-DD HH:mm');
									msg_data += '<p>' + '時間:' + timeFormat + '</p>'
									msg_data += '<p>' + '天氣:' + wxStatus.wxValue[i] + '</p>'
									msg_data += '<p>' + '溫度:' + temperatureStatus.temperature[i] + '</p>'
									msg_data += '<p>' + '降雨機率:' + rainfall.rain[i] + '</p>'
									msg_data += '</div>'
								}
								document.getElementById("weather").innerHTML = msg_data
							}

						}).catch(error => {
							console.log('error data', error)
						})
					}
				</script>

				<!-- footer -->
				<jsp:include page="layout/footer.jsp"></jsp:include>
				<!-- jquery -->
				<script src="${contextRoot}/js/jquery-3.6.3.min.js"></script>

				<!-- Vendor JS Files -->
				<script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
				<script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
				<script src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
				<script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
				<script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

				<!-- Template Main JS File -->
				<script src="${contextRoot}/assets/js/main.js"></script>
			</body>

			</html>