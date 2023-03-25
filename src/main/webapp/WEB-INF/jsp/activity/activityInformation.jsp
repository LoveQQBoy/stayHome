<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>活動資訊</title>
			</head>

			<body>
				<jsp:include page="../layout/sideNavbar.jsp"></jsp:include>

				<!-- 右邊 -->
				<div class="container-right">
					<div class="container">
						<div class="row">
							<div class="mt-5 header-border">
								<h1>活動瀏覽</h1>
							</div>
							<!-- start -->

							<div class="mt-5">
								<img src="${contextRoot}/activity/activityPicture?activityId=${activityInformation.p_Id}"
									onerror="this.src='${contextRoot}/download/未上傳圖片.png'"
									style="max-width:300px; max-height: 300px ">
							</div>
							<div class="mt-3">
								<p>活動名:${activityInformation.title}</p>
								<p>主旨:${activityInformation.text}</p>
								<p>講師名稱:${activityInformation.activityTeacher}</p>
								<p>地點:${activityInformation.place}</p>
								<p>講師聯絡方式:${activityInformation.teacherPhone}</p>
								<p>報名人數:${activityInformation.applicantNumber}/${activityInformation.applicantNumberFull}
								</p>
								<p>報名狀態:${activityInformation.applicantStatus}</p>
								<P>
									報名截止日:
									<fmt:formatDate value="${activityInformation.applicantDeadDate}"
										pattern="yyyy-MM-dd HH:mm" />
								</P>
							</div>
							<!-- end -->
							<div style="margin-top:100px;">
								<table class="table" id="applicantTable">
									<thead>
										<tr>
											<td>報名者</td>
											<td>報名日期</td>
											<td>報名狀態</td>
											<td>動作</td>
										</tr>
									</thead>
									<tbody>

									</tbody>

								</table>
								<div id="pageButton">

								</div>
							</div>
						</div>

					</div>
				</div>

				<script>

					window.onload = function () {
						applicantPage(event);
					}

					function applicantPage(event) {
						fetch('${contextRoot}/activity/applicantFindAllPage?activityId=${activityInformation.p_Id}', {
							method: 'get',
							headers: { 'Content-Type': 'application/json' }
						}).then(response => {
							return response.json();
						}).then(responsePage => {
							tbodyCreate(responsePage)
							console.log(responsePage.content)
						});
					}
					function tbodyCreate(data) {

						//時間做轉換
						function formatDate(date) {
							return moment(date).format('YYYY-MM-DD HH:mm')
						}
						//時間轉換
						msg_data = '<tbody>'
						data.content.forEach(page => {
							let status = page.applicantStatus
							console.log(status)
							console.log(page.householdData)
							if (status === '已取消') {
								console.log("hahaha")
							}
							msg_data += '<tr>'
							msg_data += '<td>' + page.householdData.name + '</td>'
							msg_data += '<td>' + formatDate(page.registrationDate) + '</td>'
							msg_data += '<td>' + page.applicantStatus + '</td>'
							msg_data += '<td>' +
								chechApplicantStatus(page.applicantStatus)
								+ '</td>'
							msg_data += '</tr>'

							//判斷是否是有取消
							function chechApplicantStatus(applicantStatus) {
								if (applicantStatus == '已取消') {
									return ''
								} else {
									let create = `<form action="${contextRoot}/activity/applicantCancleByManager" method="post">
									<input name="_method" type="hidden" value="Put">
									<input name="activityId" type="hidden" value="\${page.activity.p_Id}">
									<input name="memberId" type="hidden" value="\${page.householdData.id}">
									<button type = "submit" class="btn btn-outline-success" >取消報名</button >
									</form >`
									return create
								}
							}

						})
						msg_data += '</tbody>'
						let table = document.getElementById("applicantTable");
						table.getElementsByTagName('tbody')[0].innerHTML = msg_data

						//選頁數
						let totalPages = data.totalPages;
						let button_data = ''
						button_data += '<div>'
						for (var i = 1; i <= totalPages; i++) {
							button_data += '<button class="buttonPage" data-pageNum="' + i + '">' + i + '</button>'
						}
						button_data += '</div>'
						document.getElementById('pageButton').innerHTML = button_data;


						let pageBtns = document.getElementsByClassName('buttonPage');
						for (i = 0; i < pageBtns.length; i++) {
							pageBtns[i].addEventListener("click", function (event) {
								let pageNumber = this.getAttribute('data-pageNum');
								loadPage(pageNumber);
							})
						}
					}

					//選完頁數後自動刷新內容
					function loadPage(page) {
						fetch('${contextRoot}/activity/applicantFindAllPage?activityId=${activityInformation.p_Id}&pageNumber=' + page, {
							method: 'get'
						}).then(res => {
							return res.json();
						}).then(successPage => {
							tbodyCreate(successPage)
						})
					}

				</script>

			</body>

			</html>