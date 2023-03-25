<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>userDemo</title>
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>

</style>

<script>

//document.getElementById("refuseToAcceptButton").addEventListener("click", function() {
	  // 禁用按钮
	  //document.getElementById("refuseToAcceptButton").disabled = true;
	  // 调整样式
	 // document.getElementById("refuseToAcceptButton").classList.add("disabled");
//});
</script>
</head>

<body>

	<jsp:include page="../layout/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="container">
			<div class=" w-60 mt-5">
				<h3 style="padding: 30px">代收包裹</h3>
				<div style="margin-left: 30px; color: green;">代收包裹規定:</div>
				<div style="margin-left: 30px; color: green;">1.不代收冷藏、冷凍包裹。</div>
				<div style="margin-left: 30px; color: green;">
					2.住戶須在20天內領取包裹,以到貨當天開始算做一天。出國或是有事情沒辦法領取包裹,要事先打電話通知。電話:07-xxxxxxx。</div>
				<div style="margin-left: 30px; color: green;">
					3.領取時,要核對能證明身分的證件(身分證、駕照、健保卡),以確認身分。</div>
				<div style="margin-left: 30px; color: green;">
					4.代領別人包裹時,要帶對方能證明身分的證件(身分證、駕照、健保卡),以確認身分。</div>
				<div style="margin-left: 30px; color: green;">5.領取地點在 xxx。</div>
			</div>
		</div>
		<div class="row">
			<div style="padding: 17px;">
				<!-- <div class="offset-sm-2 col-sm-8 my-5"> -->

				<form method="get" action="${contextRoot}/collectParcel/userDemo"
					id="UserParcelForm">
					<div class="row">
						<div class="col-sm-0">
							<input type="hidden" name="id" value="${householdData.id}">

						</div>
						<div style="margin-left: 37px;" class="col">

							<label for="PickedupPackage">未領取</label> <input type="radio"
								name="state" id="PickedupPackage" value="未領取" /> <label
								for="NotPickedupPackage">已領取</label> <input type="radio"
								name="state" id="NotPickedupPackage" value="已領取" /> <span
								style="margin-left: 30px;"><button type="submit"
									class="btn btn-primary">查詢</button></span>
							<button type="reset" class="btn btn-info">重設</button>


						</div>


					</div>

				</form>
			</div>
		</div>
		<div class="container">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
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
						<c:set var="household" value="${ctp.packHouseholdData}"></c:set>
						<tr>
							<!-- <td>
								${ctp.state}
								<form  method="get" action="${contextRoot}/collectParcel/refuseToAccept">
								<input type="hidden" name="id" value="${ctp.id}">
								<button type ="submit" class="btn btn-success mr-2" id=refuseToAcceptButton>拒收</button></form>
							</td>
							-->
							<td>${ctp.state}</td>
							<td>${ctp.parcelname}</td>
							<td>${household.name}</td>
							<td>${household.phone}</td>
							<td>${household.address}</td>
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
						<a
							href="${contextRoot}/collectParcel/userDemo?p=${pageNumber}&id=${id}&state=${state}">${pageNumber}</a>
					</c:when>

					<c:otherwise>
  						${pageNumber}
 						</c:otherwise>
				</c:choose>

			</c:forEach>
		</div>
	</div>
</body>

</html>