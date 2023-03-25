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
<title>編輯留言</title>
</head>
<body>
<jsp:include page="../layout/navbar.jsp"></jsp:include>

	<!-- Start Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<h2>編輯留言</h2>
			<p>住戶反應有問題都可以更改
			</p>
		</div>
	</div>
	<!-- End Breadcrumbs -->

	<div class="container mt-5">
		<div class="row">
		<h1>編輯留言</h1>
		
		<div class="card p-0">
						<!--action= 執行方法的路徑 ，modelAttribute=作用是將表單提交的數據綁定到名稱為 msg 的對象中(如果前面的步驟就有送model，那3個地方的名子要一樣)，並將此對象傳遞給相應的 Controller 方法。 就可以使用 ModelAttribute 注解將此對象作為方法參數接收。  -->
			<form:form action="${contextRoot}/feedback/editpost" modelAttribute="msg" method="post" class="w-100"> <%-- <form:form>是Spring的表單標籤庫，它用於在JS中創建表單。modelAttribute屬性指定表單綁定的模型屬性名稱。在這個例子中，模型屬性名稱是"feedback"，這意味著當用戶提交表單時，Spring將會創建一個Feedback對象，並填充表單中的輸入值到這個對象中。這個表單提交到哪個URL上由表單的action屬性指定。  --%>
				<form:input type="hidden" path="feedbackId"/>
				<form:input type="hidden" path="feedbackDate"/>
				<div class="card-header ">標題:
					<div class="col-4">
					<form:input path="feedbackName" type="text" name="feedbackName" class="form-control"/>
					</div>
				<div>${name}</div>
				</div>
				<div class="card-body">意見內容:
					<div class="input-group">
						<form:textarea path="feedbackContent" class="form-control" rows="" cols=""/><%--<form:textarea>是Spring的表單標籤庫，它用於在JS中創建多行文本輸入框。path屬性指定與模型對象中的屬性名稱綁定的輸入框的名稱。在這個例子中，path屬性被設置為"feedbackContent"，這意味著當表單被提交時，Spring將會自動把用戶在這個輸入框中輸入的值設置到Feedback對象的"feedbackContent"屬性中。 --%>
					</div>
					<br />
					<button type="submit" class="btn btn-primary">送出</button>
				</div>
			</form:form>
		</div>
		</div>
	</div>

</body>
</html>