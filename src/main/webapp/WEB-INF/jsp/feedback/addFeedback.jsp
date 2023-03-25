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
<title>留言</title>
</head>

<body>
	<jsp:include page="../layout/navbar.jsp"></jsp:include>
	
	<!-- ======= Breadcrumbs ======= -->
  	 <div class="breadcrumbs mb-5">
     	<div class="container">
       	<h2>意見反應</h2>
       	<p>大家的意見都可以在這邊提出來喔
       	</p>
    		</div>
   	 </div>
 	 <!-- End Breadcrumbs -->

	<div class="container">
		<h1>留言</h1>
		<div class="card">
			<form:form action="${contextRoot}/feedback/post" modelAttribute="feedback" method="post"> <%-- <form:form>是Spring的表單標籤庫，它用於在JSP中創建表單。modelAttribute屬性指定表單綁定的模型屬性名稱。在這個例子中，模型屬性名稱是"feedback"，這意味著當用戶提交表單時，Spring將會創建一個Feedback對象，並填充表單中的輸入值到這個對象中。這個表單提交到哪個URL上由表單的action屬性指定。  --%>
				<div class="card-header">主題:
					<form:input path="feedbackName" type="text" name="feedbackName"/>
					<div class="text-danger"><form:errors path="feedbackName"/></div>
				<div>${name}</div>
				</div>
				<div class="card-body">意見內容:
					<div class="input-group">
						<form:textarea path="feedbackContent" class="form-control" rows="" cols=""/><%--<form:textarea>是Spring的表單標籤庫，它用於在JSP中創建多行文本輸入框。path屬性指定與模型對象中的屬性名稱綁定的輸入框的名稱。在這個例子中，path屬性被設置為"feedbackContent"，這意味著當表單被提交時，Spring將會自動把用戶在這個輸入框中輸入的值設置到Feedback對象的"feedbackContent"屬性中。 --%>
						<div class="text-danger"><form:errors path="feedbackContent"/></div>
					</div>
					<br />
					<button type="submit" class="btn btn-primary">送出</button>
				</div>
			</form:form>
		</div>
	<%--	<div class="card">
			<div class="card-header">
			<!--  fmt:formatDate pattern="yyyy-MM-dd E HH:mm:ss"   http://tw.gitbook.net/jsp/jstl_format_formatdate_tag.html -->
			訊息時間:<span><fmt:formatDate pattern="yyyy-MM-dd E HH:mm:ss" value="${latestMsg.feedbackDate}"/></span>
			<br/>
			<span>標題:${latestMsg.feedbackName}</span>
			
			</div>
			<div class="card-body">${latestMsg.feedbackContent}</div>
		</div> --%>
	</div>
	
	
	
	
</body>
</html>