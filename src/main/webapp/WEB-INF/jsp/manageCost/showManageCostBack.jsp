<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
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
			  }
			})
		});
		
		if($("#deleteDone").val()=="no"){
			Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: '已有人繳費，不可刪除',
				})
		}
	});
</script>
</head>
<body>
	<jsp:include page="../layout/sideNavbar.jsp" />
	<div class="container-right">
		<div class="row">
			<!-- ---------------------------程式區-------------------------------- -->
			<h1>管理費</h1>
			
			<div class="d-flex flex-row-reverse">
				<a class="btn btn-warning mb-2"
					href="${contextRoot}/manageCost/addManageCostPage">+新增</a>
			</div>
			
			<div>
				<table class="table table-bordered border-dark align-middle text-center">
					<thead class="table-secondary">
						<tr>
							<th scope="col">標題</th>
							<th scope="col">期別</th>
							<th scope="col">創建時間</th>
							<th scope="col">狀態</th>
							<th scope="col">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="manageList" items="${mclPage.content}">
							<tr>
								<td>
									<c:choose>
										<c:when test="${manageList.mcState == 0}">
											<a href="${contextRoot}/manageCost/manageCostDetailPage/${manageList.id}">${manageList.mcTitle}</a>
										</c:when>
										<c:otherwise>${manageList.mcTitle}</c:otherwise>
									</c:choose>
								</td>
								
								<td>${manageList.mcYear}/${manageList.mcMonth}</td>
								
								<td><span><fmt:formatDate value="${manageList.createDate }"
											pattern="yyyy/MM/dd HH:mm:ss" /></span>
								</td>
								
								<td>
									<c:choose>
										<c:when test="${manageList.mcState == 0}">未啟用	</c:when>
										<c:otherwise>啟用中</c:otherwise>
									</c:choose>
								</td>
	
								<td>
									<c:choose>
										<c:when test="${manageList.mcState == 0}">
										<div class="d-inline-flex">
											<form action="${contextRoot}/manageCost/changeState/on" method="post">
												<input type="hidden" name="_method" value="put"/>
												<input type="hidden" name="id" value="${manageList.id}"/>
												<button type="submit" class="btn btn-primary">啟用</button>
											</form>
											<form action="${contextRoot}/manageCost/deleteManageCostList" method="post" id="deleteForm">
												<input type="hidden" name="_method" value="delete"/>
												<input type="hidden" name="id" value="${manageList.id}"/>
												<button type="button" class="btn btn-danger">刪除</button>
											</form>
											<c:if test="${error=='false'}">
												<input type="hidden" id="deleteDone" value="no" />
											</c:if>
										</div>
										</c:when>
										<c:when test="${manageList.mcState == 1}">
											<div class="d-inline-flex">
											<form action="${contextRoot}/manageCost/changeState/off" method="post">
												<input type="hidden" name="_method" value="put"/>
												<input type="hidden" name="id" value="${manageList.id}"/>
												<button type="submit" class="btn btn-primary">關閉</button>
											</form>
											</div>
										</c:when>
										<c:otherwise>關閉中</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- ---------------------------程式區-------------------------------- -->
		</div>
	</div>
</body>
</html>