<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>住戶戶型更新</title>
			</head>

			<body>
				<jsp:include page="../layout/sideNavbar.jsp" />

				<div class="container-right">
					<h1>住戶戶型更新</h1>
					<div class="row">

					<!-- 原生地form 沒辦法用PUT請求  要多寫 input type="hidden" name="_method value="put" /> -->
					<form:form action="${contextRoot}/HouseType/add" modelAttribute="houseType" method="post">

						<!-- 這邊設定 form:input 是為了抓到ID  不然會變新增 type="hidden" 是讓表格隱藏-->

						<div class="mb-3">
							<label for="houseSize" class="form-label">戶型</label>
							<form:select path="houseSize" class="form-control" type="text"
								name="houseSize" value="">
								<form:option value="100">A</form:option>
								<form:option value="80">B</form:option>
								<form:option value="60">C</form:option>
								<form:option value="40">D</form:option>
							</form:select>
						</div>
						
					
						<div class="mb-3">
							<label for="houseFloor" class="form-label">樓層</label>
							<form:select path="houseFloor" class="form-control" type="text"
								name="houseFloor" value="">
								<form:option value="1">1F</form:option>
								<form:option value="2">2F</form:option>
								<form:option value="3">3F</form:option>
								<form:option value="4">4F</form:option>
								<form:option value="5">5F</form:option>
								<form:option value="6">6F</form:option>
								<form:option value="7">7F</form:option>
								<form:option value="8">8F</form:option>
								<form:option value="9">9F</form:option>
								<form:option value="10">10F</form:option>
							</form:select>
						</div>
						
						<div class="mb-3">
							<label for="parkingSpace" class="form-label">車位</label>
							<form:select path="parkingSpace" class="form-control" type="text"
								name="parkingSpace" value="">
								<form:option value="0">無車位</form:option>
								<form:option value="1">1個車位</form:option>
								<form:option value="2">2個車位</form:option>
							</form:select>
						</div>
						
						<input type="hidden" name="householdId" value="${householdId}">
						<button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
					

					</div>
				</div>

			</body>

			</html>