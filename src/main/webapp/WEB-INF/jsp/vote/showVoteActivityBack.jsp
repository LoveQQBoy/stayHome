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
			<h1>社區管理系統</h1>
		<div>
			<form action="${contextRoot}/vote/voteBackPage" method="get">
				<table class="table table-bordered border-dark align-middle text-center">
					<tbody>
						<tr>
							<th>標題</th>
							<th><input type="text" name="title" /></th>
							<th>狀態</th>
							<th>
								<input type="radio" name="useState" id="unused" value="1"/>
								<label for="unused">待啟用</label>
								<input type="radio" name="useState"	id="using" value="2"/>
								<label for="using">投票中</label>
								<input type="radio" name="useState" id="used" value="3"/>
								<label for="used">已結束</label>
							</th>
							<th>
								<button type="submit" class="btn btn-primary">查詢</button>
								<button type="reset" class="btn btn-primary">重設</button>
							</th>
						</tr>
					</tbody>
				</table>
			</form>
		</div>

		<div class="d-flex flex-row-reverse">
			<a class="btn btn-warning mb-2"
				href="${contextRoot}/vote/createVotePage">+發起投票</a>
		</div>

		<div>
			<table class="table table-bordered border-dark align-middle text-center">
				<thead class="table-secondary">
					<tr>
						<th scope="col">標題</th>
						<th scope="col">投票時間</th>
						<th scope="col">狀態</th>
						<th scope="col">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vote" items="${vaPage.content}">
						<tr>
							<td>${vote.title}</td>

							<td><span><fmt:formatDate value="${vote.startDate }"
										pattern="yyyy-MM-dd" /></span>~<span><fmt:formatDate
										value="${vote.endDate }" pattern="yyyy-MM-dd" /></span></td>

							<td>
								<c:set var="nowDate"><fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd"/></c:set>
								<c:set var="sDate"><fmt:formatDate value="${vote.startDate}" pattern="yyyy-MM-dd"/></c:set>
								<c:set var="eDate"><fmt:formatDate value="${vote.endDate}" pattern="yyyy-MM-dd"/></c:set>
								<c:choose>
									<c:when test="${nowDate < sDate}">待啟用	</c:when>
									<c:when test="${nowDate > eDate}">已結束	</c:when>
									<c:otherwise>投票中</c:otherwise>
								</c:choose>
							</td>

							<td>
								<div class="d-inline-flex">
									<form action="${contextRoot}/vote/editVotePage" method="get">
										<input type="hidden" name="id" value="${vote.id}"> 
										<button type="submit"	class="btn btn-success">編輯</button>
									</form>
									<form id="deleteForm" action="${contextRoot}/vote/deleteVote"	method="post">
										<input type="hidden" name="id" value="${vote.id}"> 
										<input type="hidden" name="_method" value="delete">
										<button type="button" class="btn btn-danger">刪除</button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:forEach var="pageNum" begin="1" end="${vaPage.totalPages}">
				<c:choose>
					<c:when test="${pageNum != (vaPage.number+1)}">
						<c:choose>
							<c:when test="${title==null || useState==null }">
								<a class="btn btn-outline-primary" href="${contextRoot}/vote/voteBackPage?page=${pageNum}">${pageNum}</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-outline-primary" 
									href="${contextRoot}/vote/voteBackPage?page=${pageNum}&title=${title}&useState=${useState}">${pageNum}</a>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary">${pageNum}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<!-- ---------------------------程式區-------------------------------- -->
		    </div>
    </div>

</body>
</html>