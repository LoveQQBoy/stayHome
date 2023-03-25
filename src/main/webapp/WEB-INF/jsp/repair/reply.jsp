<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript"
	src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript"
	src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	$(function() {
		$("form").on("submit", function(e) {
			e.preventDefault();
			swal({
				  title: "您確定送出?",				 
				  icon: "warning",
				  buttons: true,
				})
				.then((willDelete) => {
				  if (willDelete) {			   
				    this.submit();
				  } 
				});
		});
	})
</script>
</head>

<body>
	<jsp:include page="../layout/sideNavbar.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>後台維修列表</h2>
			<p>有看到任何器材損壞了嗎?都可以來這邊回報喔!</p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	<div class="container-right">
		<div class="row">

			<!-- ---------------------------程式區-------------------------------- -->

			<h6 class="mt-3">維修編號 : ${repair.id}</h6>
			<h6>
				時間 :
				<fmt:formatDate value="${repair.date}"
					pattern="yyyy-MM-dd ,a hh:mm:ss" />
			</h6>
			<h6>
				回覆 :
				<c:if test="${repair.replyState == '1'}">
					<i class="fa fa-check-square"
						style="position: relative; top: 2px; font-size: 20px; color: green"></i>

					<br>回覆時間 : <fmt:formatDate value="${repair.replyTime}"
						pattern="yyyy-MM-dd ,a hh:mm:ss" />

				</c:if>
				<c:if test="${repair.replyState == '0'}">
					<i class="material-icons"
						style="position: relative; top: 4px; font-size: 20px; color: red">cancel</i>
				</c:if>
			</h6>

			<br />

			<div class="mb-3">
				<h6 class="fw-bold">留言 :</h6>
				<p>${repair.description}</p>
			</div>

			<div class="mb-3">
				<h6 class="fw-bold">照片 :</h6>
				<img style="height: 350px"
					src="<c:url value='/repairPicture/${repair.id}'/>" />
			</div>


			<form action="${contextRoot}/repair/reply/${repair.id}" method="post">
				<label for="replyText">回覆(限百字內):</label>
				<textarea name="reply" class="form-control" id="replyText" rows=""
					cols="" required>${repair.reply}</textarea>
				<div class="text-danger">${notSpace}</div>
				<br />

				<button class="btn btn-primary" type="submit">送出</button>
				<button class="btn btn-primary" type="button"
					onclick="location.href='${contextRoot}/repair/backList?p=${pageNumberS}&date=${date}&replyState=${replyState}&repairName=${repairName}&phone=${phone}'">取消</button>
			</form>

			<!-- ---------------------------程式區-------------------------------- -->

		</div>
	</div>

</body>
</html>