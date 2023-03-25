<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>活動修改</title>
			</head>

			<body>

				<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>

				<!-- 右邊 -->
				<div class="container-right">

					<div class="container">
						<div class="row">
							<div class="mt-5 header-border">
								<h1>活動修改</h1>
							</div>
							<div class="offset-2 col-8 mt-5">
								<form action="${contextRoot}/activity/updateFinish" method="post"
									enctype="multipart/form-data">


									<div>
										<img id="activityPicture" alt="#" src=""
											style="max-width: 300px; max-height: 300px;"
											onerror="this.src='${contextRoot}/download/未上傳圖片.png'">

									</div>
									<br>
									<div class="input-group mb-3">
										<label class="input-group-text" for="inputGroupFile01">活動圖片</label>
										<input type="file" class="form-control" id="picture" name="picture"
											value="${activityInformation.picture}">
									</div>

									<div class="input-group mb-3">
										<span class="input-group-text">標題</span>
										<input type="text" name="title"
											value="${activityInformation.title }${rememberMessage.title}">&nbsp;<span
											style="color: red;margin: auto 5px;">${errorMessage.title}</span>
									</div>
									<div class="input-group">
										<span class="input-group-text">活動內容</span>
										<textarea class="form-control" aria-label="With textarea"
											name="text">${activityInformation.text}${rememberMessage.text}</textarea>&nbsp;<span
											style="color: red;margin: auto 5px;">${errorMessage.text}</span>
									</div>
									<br>
									<div class="input-group mb-3">
										<span class="input-group-text">講師</span>
										<input type="text" name="activityTeacher"
											value="${activityInformation.activityTeacher }${rememberMessage.activityTeacher}">
										&nbsp;<span
											style="color: red;margin: auto 5px;">${errorMessage.activityTeacher}</span>
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">地點</span>
										<input type="text" name="place"
											value="${activityInformation.place }${rememberMessage.place}">
										&nbsp;<span style="color: red;margin: auto 5px;">${errorMessage.place}</span>
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">講師電話</span>
										<input type="number" name="teacherPhone"
											value="${activityInformation.teacherPhone }${rememberMessage.teacherPhone}"
											pattern="[0-9]+" required>
										&nbsp;<span
											style="color: red;margin: auto 5px;">${errorMessage.teacherPhone}</span>
									</div>

									<input type="hidden" name="applicantNumber"
										value="${activityInformation.applicantNumber}${rememberMessage.applicantNumber}">

									<div class="input-group mb-3">
										<span class="input-group-text">報名人數上限</span>
										<input type="number" name="applicantNumberFull"
											value="${activityInformation.applicantNumberFull }${rememberMessage.applicantNumberFull}"
											pattern="[0-9]+" required>&nbsp;<span
											style="color: red;margin: auto 5px;">${errorMessage.applicantNumberFull}</span>
									</div>

									<input type="hidden" name="applicantStatus"
										value="${activityInformation.applicantStatus}${rememberMessage.applicantStatus}">

									<input type="hidden" name="applicantCreateDate" id="applicantCreateDate"
										value="${activityInformation.applicantCreateDate}${rememberMessage.applicantCreateDate}">

									<div class="input-group ">
										<span class="input-group-text">報名截止日期</span>
										<input type="datetime-local" id="applicantDeadDateString"
											name="applicantDeadDateString"
											value="${activityInformation.applicantDeadDate}${rememberMessage.applicantDeadDate}">
										<input type="hidden" id="applicantDeadDate" name="applicantDeadDate">&nbsp;<span
											style="color: red;margin: auto 5px;">${errorMessage.applicantDeadDate}</span>
									</div>
									<input type="hidden" name="activityPid"
										value="${activityInformation.p_Id}${rememberMessage.activityPid}">
									<br>
									<button type="submit" class="btn btn-info">送出</button>
								</form>
							</div>
							<script>
								$("form").submit(function (e) {
									//e.preventDefault();



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



							</script>

							<!-- end -->


						</div>
					</div>
				</div>
				</div>
			</body>

			</html>