<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

			<!DOCTYPE html>
			<html>			

			<head>
				<meta charset="UTF-8">
				<title>註冊</title>
			</head>

			<body>
			

				<jsp:include page="../layout/navbar.jsp"></jsp:include>

				<!-- ======= Breadcrumbs ======= -->
				<div class="breadcrumbs">
					<div class="container">
						<h2>會員註冊</h2>
					</div>
				</div><!-- End Breadcrumbs -->
				<form:form action="${contextRoot}/household/post"  modelAttribute="householdData" method="post">
					<div class="container w-75">
						<div class="row">
							<div class="my-5 col-6" data-aos="fade-down">
							
							
					
								
								
								<div class="mb-3">
									<label for="name" class="form-label">姓名</label>
									<form:input path="name" class="form-control" type="text" name="name" placeholder="請輸入姓名" value="" />
									<div class="text-danger"><form:errors path="name" /></div>
								</div>

								<div class="mb-3">
									<label for="phone" class="form-label">電話</label>
									<form:input path="phone" class="form-control" type="text" name="phone" placeholder="請輸入包含(區碼)電話" value=""
										id="phone" onblur="checkPhone()" />
									<div class="text-danger"><form:errors path="phone" /></div>
								</div>

								<div class="mb-3">
									<label for="password" class="form-label">密碼</label>
									<form:input path="password" type="password" class="form-control" id=""  placeholder="密碼最少6碼，最多20碼"/>
									<div class="text-danger"><form:errors path="password" /></div>
								</div>

								<div class="mb-3">
									<label for="birthday" class="form-label">生日</label>
									<form:input path="birthday" class="form-control" type="date" name="birthday"
										value="" />
								</div>
								<div class="mb-3">
								<div class="text-danger">${error}</div>
									<label for="email" class="form-label">E-MAIL</label>
									<form:input path="email" class="form-control" type="email" name="email" id="email"
										pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" value=""
										onblur="checkEmail()" />
									<div class="text-danger"><form:errors path="email" /></div>
									<div id="emailHelp" class="form-text">請輸入E-MAIL正確格式</div>
								</div>

								<div class="mb-3">
									<label for="address" class="form-label">地址</label>
									<form:input path="address" class="form-control" type="text" name="address"
										value="" placeholder="地址必須填寫"/>
										<div class="text-danger"><form:errors path="address" /></div>
								</div>

								<!-- 測試OK就刪除 -->
								<!-- <button type="submit" class="btn btn-outline-success" data-aos="fade-down">提交</button> -->
								<!-- 測試OK就刪除 -->
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-outline-success" data-bs-toggle="modal"
									 data-bs-target="#exampleModal">
									提交
								</button>
							</div>
						</div>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-dialog-scrollable">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="" id="exampleModalLabel">住戶守則</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body ">
									<p>
										一、
										本公寓大廈專有部分、共用部分、約定專用部分、約定共用部分之範圍界定如后，其區劃界限詳如附件一標的物件之圖說。
										（一）
										專有部分：係指編釘獨立門牌號碼或所在地址證明之家戶，並登記為區分所有權人所有者。
										（二）
										共用部分：係指不屬專有部分與專有附屬建築物，而供共同使用者。
										（三）
										約定專用部分：公寓大廈共用部分經約定供特定區分所有權人使用者，使用者名冊由管理委員會造冊保存。
										（四）
										約定共用部分：公寓大廈專有部分經約定供共同使用者。
										二、
										本公寓大廈法定空地、樓頂平臺為共用部分，應供全體區分所有權人及住戶共同使用，非經規約或區分所有權人會議之決議，不得約定為約定專用部分。
										三、
										本公寓大廈周圍上下及外牆面為共用部分，由全體區分所有權人維護其外觀使用，非經規約或區分所有權人會議之決議，不得懸掛或設置廣告物。
										四、
										停車空間應依與起造人或建築業者之買賣契約書或分管契約書使用其約定專用部分。無買賣契約書或分管契約書且為共同持分之停車空間經區分所有權人會議決議者，得將部分之停車空間約定為約定專用部分供特定區分所有權人使用，其契約格式如附件二。
										五、
										下列各目所列之特定對象基於業務上之必要，得無償使用該共用部分或約定共用部分：
										（一）
										受託管理業務或承包工作者。
										（二）
										○○電力公司。
										（三)
										○○瓦斯公司。
										（四）
										電信機構。
										（五）
										自來水機構。
										六、
										區分所有權人及住戶對於陽台不得違建；如需裝置鐵窗時，不得妨礙消防逃生及救災機能，應先經管理委員會同意，方得裝設。
										七、
										共用部分及約定共用部分劃設機車停車位，供住戶之機車停放，其相關管理規範依區分所有權人會議決議為之。
									</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">不同意</button>
									<button type="submit" class="btn btn-success"
										style="background-color: #5fcf80;">同意</button>
								</div>
							</div>
						</div>
					</div>
					<!-- model結束 -->
				</form:form>
				
			

				<!-- footer -->
				<jsp:include page="../layout/footer.jsp"></jsp:include>
				<!-- Vendor JS Files -->
				<script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
				<script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
				<script src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
				<script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
				<script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

				<!-- Template Main JS File -->
				<script src="${contextRoot}/assets/js/main.js"></script>
				 <!-- sweetAlert -->
       			 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
			</body>

			</html>
