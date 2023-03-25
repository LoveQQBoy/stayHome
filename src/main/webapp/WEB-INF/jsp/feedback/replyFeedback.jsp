<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> <%-- 使用spring form 要寫的 --%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>回覆留言</title>
</head>
<body>
<jsp:include page="../layout/navbar.jsp"></jsp:include>
	
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>意見反應</h2>
			<p>大家的意見都可以在這邊提出來喔</p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	
	<div class="container">
	<div class="row row-cols-1">
	
		<h3>  </h3>

	<!----------------------------------- 公告主題 --------------------------------------------->	
		<div class="card">
			<div class="card-header d-flex bd-highlight">		
				<div class="flex-grow-1 bd-highlight">主題:${feedback.feedbackName}</div>
				<div class="flex-grow-1 bd-highlight">發表人:${feedback.householdData.name}</div>
				發表時間:<div class="d-flex flex-row-reverse"><span><fmt:formatDate pattern="yyyy-MM-dd E HH:mm:ss" value="${feedback.feedbackDate}"/></span></div>
			</div>
			
			<div class="card-body">
				${feedback.feedbackContent}
			</div>	
		</div>
	</div>
	<br/>
	<!----------------------------------意見回覆內容--------------------------------------------->		
		<div class="row row-cols-3">
		
			<c:forEach var="responseByPage" items="${responseByPage.content}">
			<div class="col d-flex justify-content-evenly">
				<div class="card border-success mb-3 " style="max-width: 18rem;">
					  <div class="card-header bg-transparent border-success">${responseByPage.householdData.name}</div>
					  <div class="card-body text-success">
					    <p class="card-text">${responseByPage.responseContent}</p>
					  </div>
					  <div class="card-footer bg-transparent border-success">
					  <span><fmt:formatDate pattern="yyyy-MM-dd E HH:mm:ss" value="${responseByPage.responseDate}"/></span>
					  </div>				  
				</div>
			</div>
			</c:forEach>	
		
		</div>
	<!----------------------------------分頁設定------------------------------------------------>
	<ul class="pagination pagination-sm">
		<%--從1開始，到feedbackByPage.totalPages結束，每個頁碼的變數名稱為pageNumber。這通常用於顯示分頁的頁碼連結，使用戶可以輕鬆地瀏覽長列表或搜索結果的不同頁面。 --%>
			<c:forEach var="pageNumber" begin="1" end="${responseByPage.totalPages}">
				<c:choose>
					<c:when test="${responseByPage.number != pageNumber-1}">
						    <li class="page-item">
						     	<a class="page-link" href="${contextRoot}/feedback/replypage/${feedback.feedbackId}?p=${pageNumber}">${pageNumber}</a>
						    </li>					    
					</c:when>
					
					<c:otherwise>
						<li class="page-item active"><a class="page-link" href="${contextRoot}/feedback/replypage/${feedback.feedbackId}?p=${pageNumber}">${pageNumber}</a></li>
					</c:otherwise>
				</c:choose>			
			</c:forEach>
		</ul>
	
	
	<!--------------------------------------意見回覆--------------------------------------------->
		
		<div>
           <form:form method="post" action="${contextRoot}/response/add" modelAttribute="response">
              <div class="input-group">
                 <span class="input-group-text">意見回覆</span>
                 <form:textarea path="responseContent" class="form-control" aria-label="With textarea"/>
              </div>
                  <input type="hidden" name="feedbackId" id="feedbackId" value="${feedback.feedbackId}">
                  <button type="submit" class="btn btn-info">送出</button>
            </form:form>
        </div>
     
	
	</div>

</body>
</html>