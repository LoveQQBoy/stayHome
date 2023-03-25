<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>公告更新</title>
			</head>

			<body>
				<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>


				<!-- 右邊 -->
				<div class="container-right">

					<div class="container">
						<div class="row">
							<div class="mt-5 header-border">
								<h1>公告修改</h1>
							</div>
							<!-- start -->
							<div class="offset-2 col-8 mt-5">
								<form
									action="${contextRoot}/announcement/announcement/${announcement.p_Id}${rememberMessage.announcementId}"
									method="post" enctype="multipart/form-data">
									<input type="hidden" name="_method" value="put" />
									<input type="hidden" name="pictureName" value="${announcement.pictureName}" />

									<div>
										<img id="activityPicture" alt="#" src=""
											style="max-width: 300px; max-height: 300px;"
											onerror="this.src='${contextRoot}/download/未上傳圖片.png'">
									</div>
									<br>
									<div class="input-group mb-3">
										<label class="input-group-text" for="inputGroupFile01">標題圖片</label>
										<input type="file" class="form-control" id="picture" name="picture">
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">標題</span>
										<input type="text" name="title"
											value="${announcement.title }${rememberMessage.title}">${errorMessage.title}
									</div>
									<div class="input-group">
										<span class="input-group-text">活動內容</span>
										<textarea class="form-control" aria-label="With textarea"
											name="text">${announcement.text}${rememberMessage.text}</textarea>${errorMessage.text}
									</div>
									<br>
									<div class="input-group mb-3">
										<label class="input-group-text" for="inputGroupFile01">相關照片</label>
										<input type="file" class="form-control" id="pictureMany" name="pictureMany"
											multiple>
									</div>
									<br>
									<input type="hidden" name="announcementDate"
										value="${announcement.announcementDate }${rememberMessage.announcementDate}">
									<br>
									<button type="submit" class="btn btn-info">送出</button>
								</form>
							</div>

							<!-- end -->
						</div>
					</div>
				</div>

				<script>
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

			</body>

			</html>