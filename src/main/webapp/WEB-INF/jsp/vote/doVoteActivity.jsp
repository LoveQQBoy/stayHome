<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<jsp:include page="../layout/navbar.jsp" />
<!-- ======= Breadcrumbs ======= -->
         	 <div class="breadcrumbs mb-5">
            	<div class="container">
              	<h2>進行投票頁面</h2>
              	<p>快點來投票喔!
              	</p>
           		</div>
          	 </div><!-- End Breadcrumbs -->
	<div class="container">
	<div class="container">
	<!-- ---------------------------程式區-------------------------------- -->
		<h1 class="text-center">進行投票頁面</h1>
		<div class="border border-dark">
			<form action="${contextRoot}/vote/addVoteRecord" method="post">
			<input type="hidden" name="voteid" value="${voteActivity.id}" />
			<input type="hidden" name="userid" value="${sessionScope.pid}"/>
				<div>
					<h4>標題</h4>
					<p>${voteActivity.title}</p>
				</div>
				<div>
					<h4>內容</h4>
					<p>${voteActivity.context}</p>
				</div>
				<c:if test="${voteActivity.select1!=null}">
					<div>
						<input type="radio" name="choose" id="select1" value="1"/>
						<label for="select1">${voteActivity.select1}</label>
					</div>
				</c:if>
				<c:if test="${voteActivity.select2!=null}">
					<div>
						<input type="radio" name="choose" id="select2" value="2"/>
						<label for="select2">${voteActivity.select2}</label>
					</div>
				</c:if>
				<c:if test="${voteActivity.select3!=null}">
					<div>
						<input type="radio" name="choose" id="select3" value="3"/>
						<label for="select3">${voteActivity.select3}</label>
					</div>
				</c:if>
				<c:if test="${voteActivity.select4!=null}">
					<div>
						<input type="radio" name="choose" id="select4" value="4"/>
						<label for="select4">${voteActivity.select4}</label>
					</div>
				</c:if>
				<c:if test="${voteActivity.select5!=null}">
					<div>
						<input type="radio" name="choose" id="select5" value="5"/>
						<label for="select5">${voteActivity.select5}</label>
					</div>
				</c:if>
				<button type="submit" class="">送出</button>
			</form>
		</div>
	<!-- ---------------------------程式區-------------------------------- -->
	</div>
</body>
</html>