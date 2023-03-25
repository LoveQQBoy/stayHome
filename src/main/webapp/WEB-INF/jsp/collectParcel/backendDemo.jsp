<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>backendDemo</title>
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
.btn {
	display: flex;
	flex-direction: row;
	align-items: center;
}
</style>

<script>

window.onload=function(){
	
}

function onSubmit() {
	// 獲取表單
	const form = document.getElementById("deleteForm");
	// 添加提交事件
	form.addEventListener("submit", function(event) {
		event.preventDefault()
		Swal.fire({
			  title: 'Are you sure?',
			  text: "You won't be able to revert this!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			  if (result.isConfirmed) {
			    form.submit()
				
		   }
		})
		
		})
	}
	// 返回false，防止頁面重新加載
	//return false;


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
				<div class=" w-60 mt-5">
					<h2>代收包裹</h2>
					<div class="row">
						<div class="offset-sm-3 col-sm-7 my-5">
<!-- 							<form method="post" action="parcel-form" id="parcelForm"> -->
							<form method="get" action="${contextRoot}/collectParcel/backendDemo" id="parcelForm">
								<div class="row">
									<label for="parcelname"
										class="col-sm-2 col-form-label text-end">包裹名稱:</label>
									<div class="col">
										<input type="text" class="form-control" id="parcelname" 
											name="parcelname" value="" />
									</div>
									<div class="col">
										<label for="PickedupPackage">未領取</label>
										<input type="radio" name="state" id="PickedupPackage" value="未領取" />
									</div>
									<div class="col">
										<label for="NotPickedupPackage">已領取</label>
										<input type="radio" name="state" id="NotPickedupPackage" value="已領取" />
									</div>
									<div class="col">
										<button type="submit" class="btn btn-primary">查詢</button>
									</div>
									<div class="col">
										<button type="reset" class="btn btn-info">重設</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>更動</th>
							<th>包裹狀態</th>
							<th>包裹名稱</th>
							<th>收件人姓名</th>
							<th>收件人電話</th>
							<th>收件人地址</th>
							<th>收到貨日期</th>
							<th>寄件人姓名</th>
							<th>詳情</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="ctp" items="${page.content}">
							<tr>
								<td><div class="d-flex">
										<form action="${contextRoot}/collectParcel/updatebeforepage"
											method="get">
											<input type="hidden" name="id" value="${ctp.id}">
											<button type="submit" class="btn btn-success mr-2">更改</button>
										</form>
										<form id="deleteForm" action="${contextRoot}/collectParcel/deleteParcel"
											method="post">
											<input type="hidden" name="id" value="${ctp.id}"> <input
												type="hidden" name="_method" value="delete">
											<button type="submit" id="deleteForm" class="btn btn-danger" onclick="onSubmit()">刪除</button>
										</form>

									</div></td>
								<td>${ctp.state}</td>
								<td>${ctp.parcelname}</td>
								<td>${ctp.packHouseholdData.name}</td>
								<td>${ctp.packHouseholdData.phone}</td>
								<td>${ctp.packHouseholdData.address}</td>
								<td>${ctp.receiveddate}</td>
								<td>${ctp.sendername}</td>
								<td>${ctp.description}</td>
							</tr>
						
						</c:forEach>
					</tbody>
				</table>
				<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">

					<c:choose>

						<c:when test="${page.number != pageNumber-1}">
							<a href="${contextRoot}/collectParcel/backendDemo?p=${pageNumber}&parcelname=${parcekname}&state=${state}">${pageNumber}</a>
						</c:when>

						<c:otherwise>
  						${pageNumber}
 						</c:otherwise>
					</c:choose>

				</c:forEach>
				<div><a href="createParcel" class="btn btn-secondary">新增包裹</a></div>
			</div>
			<!-- end -->
		</div>
	</div>

</body>

</html>