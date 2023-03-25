<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
				<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<script type="text/javascript" src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
					<script type="text/javascript" src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
					<script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
					<script>
						$(function () {

							function previewImg(files) {
								if (files.length == 0) {
									$("#img-preview").css('display', 'none');
									return;
								} else {
									$("#img-preview").css('display', 'block');
								}

								var file = files[0];

								var fr = new FileReader();
								//註冊當選檔被讀取完成後之事件處理器
								fr.onload =
									function () {
										$("#img-preview").attr({ src: fr.result });
										/*  fr.result: The file's contents. 內容如下:
											data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA ...
										*/
									};
								fr.readAsDataURL(file);
							}


							//當選檔變更時，立即預覽之前被選擇的照片
							$("#publicPhoto").change(function () {
								//$("#img-preview").attr("src","").css('display','block');
								$("#img-preview").attr("src", "")
								previewImg(this.files);
							});


							//又當使用者「返回前頁」時，需要「重新預覽」前回點選擬上傳的圖片。
							//$("#reportPhoto")是jquery物件(陣列)，$("#reportPhoto")[0]是DOM元素
							if (${ createPublic.id == null }){
							previewImg($("#publicPhoto")[0].files);
						}



						$("#form").on("submit", function (e) {
							e.preventDefault();
							swal({
								title: "您確定送出?",
								icon: "warning",
								buttons: true,
							})
								.then((willDelete) => {
									if (willDelete) {
										this.submit();
									}
								});
						});


						$("#allweek").on("click", function () {
							$(":checkbox").prop("checked", true);
						});

						$("#cancleweek").on("click", function () {
							$(":checkbox").prop("checked", false);
						})

						setTimeout(() => $("#intervalFail").html(""), 5000);

						let array;
						if (${ createPublic.daysOfWeek != null }) {
							array = "${createPublic.daysOfWeek}".split(",");

							for (let i = 0; i < array.length; i++) {
								$("[value=" + array[i] + "]").prop("checked", true);
							};
						}

// 	$("#deletebtn").on("click",function(e){
// 		e.preventDefault();
// 		swal({
// 			  title: "您確定刪除?",				 
// 			  icon: "warning",
// 			  buttons: true,
// 			})
// 			.then((willDelete) => {
// 			  if (willDelete) {	
// 				  $(this).click();
// 			  } 
// 			});
// 	})
	
	
	
});

					</script>

				</head>

				<body>

					<jsp:include page="../layout/sideNavbar.jsp" />

					<div class="container-right">
						<div class="container">
							<div class="row ">
								<h2 class="mt-5">後台新增公設</h2>

								<!-- ---------------------------程式區-------------------------------- -->

								<p1>${insertSuccess}</p1>

								<form:form action="${contextRoot}/public/createPublic" modelAttribute="createPublic"
									enctype="multipart/form-data" id="form">
									<form:input path="id" type="hidden" />
									<form:input path="date" type="hidden" />

									<label for="publicPhoto">上傳照片:</label>
									<form:input path="m_publicPhoto" type="file" id="publicPhoto" />
									<br />
									<div class="d-flex justify-content-center">
										<img src="<c:url value='/publicPicture/${createPublic.id}'/>" id="img-preview"
											style="height: 350px" />
									</div>
									<c:if test="${createPublic.date!=null}">
										<div>
											創建日期:
											<fmt:formatDate value="${createPublic.date}"
												pattern="yyyy-MM-dd ,a hh:mm:ss" />
										</div>
									</c:if>

									<label for="publicName">公設名稱:</label>
									<form:input path="publicName" id="publicName" required="required" />
									<br />

									開放天:
									<form:checkbox path="daysOfWeek" value="一" />星期一
									<form:checkbox path="daysOfWeek" value="二" />星期二
									<form:checkbox path="daysOfWeek" value="三" />星期三
									<form:checkbox path="daysOfWeek" value="四" />星期四
									<form:checkbox path="daysOfWeek" value="五" />星期五
									<form:checkbox path="daysOfWeek" value="六" />星期六
									<form:checkbox path="daysOfWeek" value="七" />星期日
									<button type="button" id="allweek">全選</button>
									<button type="button" id="cancleweek">取消</button>
									<br />

									開放時間
									<label for="openingHour">從:</label>
									<form:input type="time" path="openingHour" id="openingHour" required="required" />

									<label for="closingHour" style="display: inline;">至:</label>
									<form:input type="time" path="closingHour" id="closingHour" required="required" />
									(例如
									06:30 12:00)
									<br />

									<label for="interMinute">時段間格:</label>
									<form:input type="number" path="interMinute" id="interMinute" required="required" />
									分鐘
									<span id="intervalFail" class="text-danger">&nbsp;&nbsp;${intervalFail}</span>
									<br />

									<label for="limitNumber">人數限制:</label>
									<form:input type="number" path="limitNumber" id="limitNumber" required="required" />
									人
									<br />

									<label for="appointmentDateLimit">可預約日期前:</label>
									<form:input type="date" path="appointmentDateLimit" id="appointmentDateLimit"
										required="required" />
									<br />

									<form:radiobutton path="state" value="1" required="required" />啟用
									<form:radiobutton path="state" value="0" />停用
									<br />

									<br />
									<br />
									<button class="btn btn-primary" type="submit">送出</button>
									<c:if test="${createPublic.id!=null}">
										<button class="btn btn-danger" type="button" id="deletebtn"
											onclick="location.href='${contextRoot}/public/delete/${createPublic.id}'">刪除公設</button>
									</c:if>
									<button class="btn btn-secondary" type="button"
										onclick="location.href='${contextRoot}/public/publicList?p=${pageNumberS}&publicState=${publicState}&publicName=${publicName}'">取消</button>


								</form:form>

								<!-- ---------------------------程式區-------------------------------- -->
							</div>
						</div>
					</div>


				</body>

				</html>