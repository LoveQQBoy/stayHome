<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>新增活動</title>
			<style>
			</style>
		</head>

		<body>

			<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>



			<!-- 右邊 -->
			<div class="container-right">
				<div class="container">
					<div class="row">
						<div class="mt-5 header-border">
							<h1>活動新增</h1>
						</div>
						<div class="offset-2 col-8 mt-5">
							<!-- start -->
							<div class="container">
								<form method="post" action="${contextRoot}/activity/insertActivity"
									enctype="multipart/form-data">
									<div>
										<img id="activityPicture" src=""
											onerror="this.src='${contextRoot}/download/未上傳圖片.png'"
											style="max-width: 350px; max-height: 350px;">
									</div>
									<br>
									<div class="input-group mb-3">
										<label class="input-group-text" for="inputGroupFile01">活動圖片</label>
										<input type="file" class="form-control" id="picture" name="picture">
									</div>

									<div class="input-group mb-3">
										<span class="input-group-text">標題</span>
										<input type="text" name="title" id="title" value="${rememberMessage.title}">
										&nbsp;<span style="color: red;margin: auto 5px;">${errorMessage.title}</span>
										<span class="ms-auto"><button type="button" class="btn btn-outline-primary"
												id="autoCreate">一鍵新增</button></span>
									</div>
									<div class="input-group">
										<span class="input-group-text">活動內容</span>
										<textarea class="form-control" id="text" aria-label="With textarea"
											name="text">${rememberMessage.text}</textarea>
										&nbsp;<span style="color: red;margin: auto 5px;">${errorMessage.text}</span>
									</div>
									<br>
									<div class="input-group mb-3">
										<span class="input-group-text">講師</span>
										<input type="text" name="activityTeacher" id="activityTeacher"
											value="${rememberMessage.activityTeacher}">&nbsp;
										<span style="color: red;margin: auto 5px;">${errorMessage.activityTeacher}
										</span>
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">地點</span>
										<input type="text" name="place" value="${rememberMessage.place}" id="place">
										<span style="color: red;margin: auto 5px;">${errorMessage.place}
										</span>
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">講師電話</span>
										<input type="number" name="teacherPhone" pattern="[0-9]+" required
											id="teacherPhone" value="${rememberMessage.teacherPhone}">
										<span style="color: red;margin: auto 5px;">${errorMessage.teacherPhone}
										</span>
									</div>

									<input type="hidden" name="applicantNumber" value="0">

									<div class="input-group mb-3">
										<span class="input-group-text">報名人數上限</span>
										<input type="number" name="applicantNumberFull" pattern="[0-9]+" required
											value="${rememberMessage.applicantNumberFull}" id="applicantNumberFull">
										<span style="color: red;margin: auto 5px;">${errorMessage.applicantNumberFull}
										</span>
									</div>

									<input type="hidden" name="applicantStatus" value="未額滿">

									<input type="hidden" name="applicantCreateDate" id="applicantCreateDate">

									<div class="input-group ">
										<span class="input-group-text">報名截止日期</span>
										<input type="datetime-local" id="applicantDeadDateString"
											name="applicantDeadDateString" value="${rememberMessage.applicantDeadDate}">
										<span style="color: red;margin: auto 5px;">${errorMessage.applicantDeadDate}
										</span>
										<input type="hidden" id="applicantDeadDate" name="applicantDeadDate">
									</div>
									<br>
									<button type="submit" class="btn btn-info">送出</button>
								</form>
							</div>
							<script>
							window.onload = function(){
								autoCreate()
							}
							
								$("form").submit(function (e) {
									//e.preventDefault();
									let applicantCreateDate = new Date();
									let formattedApplicantCreateDate = moment(applicantCreateDate).format('YYYY-MM-DD HH:mm:ss');
									console.log(formattedApplicantCreateDate);
									$("#applicantCreateDate").attr("value", formattedApplicantCreateDate)


									let applicantDeadDate = $("input[name='applicantDeadDateString']").val();
									let deadDate = new Date(applicantDeadDate);
									let formatteddeadDate = moment(deadDate).format('YYYY-MM-DD HH:mm:ss');
									console.log(formatteddeadDate)
									$("input[name='applicantDeadDate']").val(formatteddeadDate).trigger("change");

								})


								$("#picture").on("change", function () {
									// 讀取選擇的文件
									let file = $(this).get(0).files[0];
									// 創建FileReader對象
									let fileReader = new FileReader();
									// 開始讀取文件
									fileReader.readAsDataURL(file);
									// 設置讀取完成事件
									fileReader.onload = function () {
										// 將讀取的圖片設置到img元素的src屬性中，實現預覽功能
										$("#activityPicture").attr('src', fileReader.result);
									}
								})

								//一鍵新增	
								function autoCreate(){
								document.getElementById("autoCreate").addEventListener("click", function () {
									$("#title").val("瑜珈特別課") 
									$("#text").val("HAMSĀLAYA (அம்சாலயம் : 生命聖殿)"+
									"是一種全方位的實踐，融合了傳統瑜珈元素，將身體、呼吸及心靈結合。 Hamsālaya Yoga以梵文和泰米爾文經典瑜伽經為基礎，融合體位法、生命能量控制法、身印法、禪定等傳授古老的智慧。") 
									$("#activityTeacher").val("黃曉明") 
									$("#place").val("台北市大同區文化路8樓") 
									$("#teacherPhone").val("0987654321") 
									$("#applicantNumberFull").val("2") 									
									$("#applicantDeadDateString").val("2023-06-01 19:00:00") 									
								})

								}

							</script>

							<!-- end -->


						</div>
					</div>
				</div>
			</div>


		</body>

		</html>