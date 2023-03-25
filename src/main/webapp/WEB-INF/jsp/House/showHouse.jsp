<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>住戶資訊管理</title>
			</head>

			<body>
			<jsp:include page="../layout/sideNavbar.jsp" />

				
				<div class="container-right">
					<h2>住戶資訊瀏覽</h2>
					<div class="row">
					<!-- c:forEach var="house" items="${householdData}"
			當model Attribute的物件多時，要用c:forEach 
		items="${householdData}是Attribute的名稱
		var 是要設定變數名稱
		
		content 是page才需要的
		 
		<c:forEach var="house" items="${householdData}">
			<div>住戶資料:${house.name}</div>
			<div>住戶資料:${house.phone}</div>
			<div>住戶資料:${house.password}</div>
			<div>住戶資料:${house.birthday}</div>

		</c:forEach>
		-->

					<table class="table table-bordered align-middle text-center">
						<thead class="table-secondary">
							<tr>
								<th scope="col">ID</th>
								<th scope="col">姓名</th>
								<th scope="col">電話</th>
								<th scope="col">戶型</th>
								<th scope="col">樓層</th>
								<th scope="col">車位數</th>
								<th scope="col"></th>


							</tr>
						</thead>

						<tbody>

							<!-- 在controller 裡面的showHouseholdPage 方法，有用hService.findAll 
  	再用  model Attribute("householdData",householdData) ，所以下方的items 欄位名稱要用householdData-->
			<c:forEach var="house" items="${householdData}">
				<tr>

					<td>${house.id}</td>
					<td>${house.name}</td>
					<td>${house.phone}</td>			
			
					<c:choose>
						<c:when test="${house.houseType.houseSize == 100}">
						<td>A</td>
						</c:when>
						<c:when test="${house.houseType.houseSize == 80}">
						<td>B</td>
						</c:when>
						<c:when test="${house.houseType.houseSize == 60}">
						<td>C</td>
						</c:when>
						<c:when test="${house.houseType.houseSize == 40}">
						<td>D</td>
						</c:when>
						<c:otherwise>
						<td>X</td>
						</c:otherwise>
					</c:choose>
					
					<td>${house.houseType.houseFloor}</td>
					<td>${house.houseType.parkingSpace}</td>


									<!-- This application has no explicit mapping for /error, 
			so you are seeing this as a fallback.
			Fri Feb 24 09:42:34 CST 2023
			There was an unexpected error (type=Bad Request, status=400).
			Failed to convert value of type 'java.lang.String' to required type 
			'java.lang.Integer'; nested exception is java.lang.NumberFormatException: 
			For input string: "house.id"
			org.springframework.web.method.annotation.MethodArgumentTypeMismatchException:
			 Failed to convert value of type 'java.lang.String' to required type 
			 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: 
			 For input string: "house.id"
			這段文字看起來是出現在使用Spring框架開發的應用程式中，當使用者嘗試訪問某個URL時出現了錯誤。
			這個錯誤通常是由於應用程式的代碼中存在錯誤，導致無法正確地處理用戶端的請求，從而返回了錯誤訊息。
			根據這段錯誤訊息，顯然是使用者在嘗試將一個字串轉換為整數時失敗了，因為該字串並不是一個有效的整數值。
			具體來說，字串 "house.id" 無法被轉換為整數。
			要解決這個問題，您需要檢查您的應用程式代碼，找出哪個地方嘗試將 "house.id" 這個字串轉換為整數值，
			並修正代碼使其能夠正確處理這種情況。如果您不知道如何修正這個錯誤，您可以尋求開發人員或技術支援人員的幫助。
			
			A:用$字號表示JSP EL表達式 才能的取到ID
             -->
					
			<td>
				<div class="d-inline-flex">
					<form action="${contextRoot}/household/editBack" method="get">
						<input type="hidden" name="id" value="${house.id}">
						<input type="submit" class="btn btn-outline-primary btn-sm" value="編輯">
					</form>
					<!-- 原生地form 沒辦法用delete請求  要多寫 input type="hidden" name="_method value="delete" /> -->
					<form id="deleteForm" action="${contextRoot}/household/delete" method="post">
						<input type="hidden" name="id" value="${house.id}">
						<input type="hidden" name="_method" value="delete">
						<button type="button" class="btn btn-outline-danger btn-sm">刪除</button>
					</form>
					
					<form action="${contextRoot}/HouseType/addpage" method="get">
						<input type="hidden" name="id" value="${house.id}">
						<input type="submit" class="btn btn-outline-success btn-sm" value="編輯戶型">
					</form>
				</div>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>


					</div>	
				</div>
			<!-- sweetAlert -->
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
				});
			</script>
			
			
		
				
			</body>

			</html>