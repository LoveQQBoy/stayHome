<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateParcel</title>
<link rel="stylesheet" href="${contextRoot}/css/style.css">
<!-- JQuery -->
<script src="${contextRoot}\js\jquery-3.6.3.min.js"></script>
<!-- moment.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<!-- js連結 -->
<script src="${contextRoot}/js/script.js"></script>
<!-- fontAwesome 連結 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
<script src="https://kit.fontawesome.com/765cc93115.js"
	crossorigin="anonymous"></script>
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>
.btn2 {
	text-align: center;
}
</style>

<script>

window.onload=function(){
	
}

function onSubmit() {
	// 獲取表單
	const form = document.getElementById("updateForm");
	// 添加提交事件
	form.addEventListener("submit", function(event) {
		event.preventDefault()
		Swal.fire({
  			title: '你確定要儲存這個變更嗎?',
  			showDenyButton: true,
  			confirmButtonText: '是的',
  			denyButtonText: `不要`,
		}).then((result) => {
			if (result.isConfirmed) {
			    form.submit()
				
		   }
		})
		
		})
		}
</script>

</head>
<body>
	<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>

	<!-- 右邊 -->
	<div class="container-right">
		<div class="row">


			<!-- start -->


			<!-- 程式碼區 -->
			<div class="container">
				<div id="h1header">
					<h2 style="text-align: center; padding: 20px">修改包裹</h2>
				</div>
				<div style="display: flex; justify-content: center;">

					<form style="display: inline-block;" action="updateParcel"
						id="updateForm" method="post">
						<input type="hidden" name="id" value="${collectParcel.id}" /> <input
							type="hidden" name="_method" value="put">
						<div style="padding: 20px 0px 10px 0px">
							<label for="state">包裹狀態:</label> <select id="state" name="state">
								<option value="未領取">未領取</option>
								<option value="已領取">已領取</option>
							</select>

						</div>
						<div>
							<label for="InputParcelName" class="form-label">包裹名稱:</label> <input
								type="text" class="form-control" id="InputParcelName"
								name="parcelname" value="${collectParcel.parcelname}">
						</div>
						<!-- <div class="mb-3">
							<label for="InputRecipientName" class="form-label">收件人姓名:</label> <input
								type="text" class="form-control" id="InputRecipientName"
								name="recipientname">
						</div>-->

						<div class="mb-3">
							<label for="InputReceivedDate" class="form-label">收到貨日期:</label>
							<input type="datetime-local" class="form-control"
								id="InputReceivedDate" name="receiveddate"
								value="${collectParcel.receiveddate}">
						</div>
						<div class="mb-3">
							<label for="InputSenderName" class="form-label">寄件人姓名:</label> <input
								type="text" class="form-control" id="InputSenderName"
								name="sendername" value="${collectParcel.sendername}">
						</div>
						<div class="mb-3">
							<label for="InputDescription" class="form-label">詳情:</label>
							<textarea cols="30" rows="3" class="form-control"
								id="InputDescription" name="description">${collectParcel.description}</textarea>
						</div>
						<button type="submit" class="btn btn-primary" onclick="onSubmit()">確認修改</button>
						<input type="reset" class="btn btn-info" value="清除"> <a
							href="backendDemo" class="btn btn-light">返回</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>