<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
		<!DOCTYPE html>
		<html>

		<head>

			<meta charset="UTF-8">
			<!-- css連結 -->
			<title>公告新增</title>
		</head>

		<body>
			<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>



			<!-- 右邊 -->
			<div class="container-right">
				<div class="container">
					<div class="row">
						<div class="mt-5 header-border">
							<h1>公告新增</h1>
						</div>
						<!-- start -->
						<div class="container">
							<div class="offset-2 col-8 mt-5 ">
								<form method="post" action="${contextRoot}/announcement/announcement"
									enctype="multipart/form-data">
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
									<c:if test="${errorMessage != null}">
										${errorMessage.pictureName}<br>
									</c:if>

									<div class="input-group mb-3">
										<span class="input-group-text">標題</span>
										<input type="text" name="title"
											value="${rememberMessage.title}" id="title">${errorMessage.title }
											<span class="ms-auto"> <button type="button" id="autoCreate" class="btn btn-outline-primary">一鑑新增</button></span>
									</div>
									<div class="input-group">
										<span class="input-group-text">活動內容</span>
										<textarea class="form-control" aria-label="With textarea"
											name="text" id="text">${rememberMessage.text}</textarea>${errorMessage.text }
									</div>
									<br>
									<div class="input-group mb-3">
										<label class="input-group-text" for="inputGroupFile01">相關照片</label>
										<input type="file" class="form-control" id="pictureMany" name="pictureMany"
											multiple>
									</div>
									<br>
									<input type="hidden" name="announcementDate" id="announcementDate">
									<br>
									<button type="submit" class="btn btn-info">送出</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<script>
				window.onload =function(){
					autoCreate()
				}
			
				$("form").submit(function (e) {
					//e.preventDefault();
					let applicantCreateDate = new Date();
					let formattedApplicantCreateDate = moment(applicantCreateDate).format('YYYY-MM-DD HH:mm:ss');
					console.log(formattedApplicantCreateDate);
					$("#announcementDate").attr("value", formattedApplicantCreateDate)


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
									$("#title").val("社區新圖書館開幕") 
									$("#text").val("社區新的圖書館已開幕,還請大家有空歡迎參觀!")
																	
				})

				}


			</script>
		</body>

		</html>