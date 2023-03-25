<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
	$(function() {
		$("#deleteForm button").click(function(event) {
			Swal.fire({
			  title: '確定刪除?',
			  text: "刪除後，相關紀錄不可復原!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				  var dForm = $(event.target).parent("#deleteForm");
				  $(dForm).submit();
// 			    Swal.fire(
// 			      'Deleted!',
// 			      'Your file has been deleted.',
// 			      'success'
// 			    )
			  }
			})
		});
	});
</script>
</head>
<body>
	<jsp:include page="../layout/sideNavbar.jsp" />
	<div class="container-right">
		<div class="row">
		<!-- ---------------------------程式區-------------------------------- -->
		<h1>我是管理費細節頁面</h1>
		<div>
			<table class="table table-bordered border-dark align-middle text-center">
				<thead class="table-secondary">
					<tr>
						<th scope="col">用戶</th>
						<th scope="col">繳費期別</th>
						<th scope="col">繳費金額</th>
						<th scope="col">繳費狀態</th>
						<th scope="col">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="managementCost" items="${managementCosts}">
						<tr>
							<td>${managementCost.manageHousehold.name}</td>
							
							<td>${managementCost.period}</td>
							
							<td>${managementCost.money}</td>
							
							<td>
								<c:choose>
									<c:when test="${managementCost.payTime == null}">未繳費</c:when>
									<c:otherwise>
										<span>
											<fmt:formatDate value="${managementCost.payTime}" pattern="yyyy/MM/dd HH:mm:ss" />
										</span>
									</c:otherwise>
								</c:choose>
							</td>
							
							<td>
							<c:choose>
								<c:when test="${managementCost.payTime == null}">
								<div>
									<form action="${contextRoot}/manageCost/deleteManageCost" method="post" id="deleteForm">
										<input type="hidden" name="_method" value="delete"/>
										<input type="hidden" name="id" value="${managementCost.id}"/>
										<button type="button" class="btn btn-danger">刪除</button>
									</form>
								</div>
								</c:when>
								<c:otherwise>已繳費</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<a href="${contextRoot}/manageCost/manageCostPageBack" class="btn btn-primary">回前頁</a>
		</div>
		<!-- ---------------------------程式區-------------------------------- -->
		</div>
	</div>
</body>
</html>