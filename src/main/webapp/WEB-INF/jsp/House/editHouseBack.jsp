<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>住戶資料更新</title>
			</head>

			<body>
				<jsp:include page="../layout/sideNavbar.jsp" />

				<div class="container-right">
					<h1>住戶資料更新</h1>
					<div class="row">
					

					<!-- 原生地form 沒辦法用PUT請求  要多寫 input type="hidden" name="_method value="put" /> -->
					<form:form action="${contextRoot}/household/editpost" modelAttribute="householdData" method="post">


						<!-- 這邊設定 form:input 是為了抓到ID  不然會變新增 type="hidden" 是讓表格隱藏-->
						<form:input path="id" type="hidden" />


						<div class="mb-3">
							<label for="name" class="form-label">姓名</label>
							<form:input path="name" class="form-control" type="text" name="name" value="" />
							<div class="text-danger"><form:errors path="name" /></div>
						</div>

						<div class="mb-3">
							<label for="phone" class="form-label">電話</label>
							<form:input path="phone" class="form-control" type="text" name="phone" value="" />
							<div class="text-danger"><form:errors path="phone" /></div>
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">密碼</label>
							<form:input path="password" type="password" class="form-control" id="" />
							<div class="text-danger"><form:errors path="password" /></div>
						</div>

						<div class="mb-3">
							<label for="birthday" class="form-label">生日</label>
							<form:input path="birthday" class="form-control" type="date" name="birthday" value="" />
						</div>


						<div class="mb-3">
							<label for="email" class="form-label">E-MAIL</label>
							<form:input path="email" class="form-control" type="email" name="email"
								pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" value="" readonly="true"/>
							<div id="emailHelp" class="form-text">帳號無法修改</div>
						</div>

						<div class="mb-3">
							<label for="address" class="form-label">地址</label>
							<form:input path="address" class="form-control" type="text" name="address" value="" />
							<div class="text-danger"><form:errors path="address" /></div>
						</div>

					
						<div class="mb-3">
							<label for="managerPermission" class="form-label">權限</label>
							<form:select path="managerPermission" class="form-control" type="text"
								name="managerPermission" value="">
								<form:option value="1">管理員</form:option>
								<form:option value="0">一般住戶</form:option>
							</form:select>
						</div>
						
					

						<button type="submit" class="btn btn-primary">提交</button>
					</form:form>
					

					</div>
				</div>

			</body>

			</html>