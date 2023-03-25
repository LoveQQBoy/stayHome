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
								<h1>公告瀏覽</h1>
							</div>
							<!-- start -->
							<div class="mt-5">
								<img src="${contextRoot}/download/${announcement.pictureName}"
									style="max-width:300px; max-height: 300px "
									onerror="this.src='${contextRoot}/download/未上傳圖片.png'">
							</div>
							<div class="mt-3">
								<p>公告名:${announcement.title}</p>
								<p>主旨:${announcement.text}</p>
								<p>公告時間:
									<fmt:formatDate value="${announcement.announcementDate}"
										pattern="yyyy-MM-dd HH:mm" />
								</p>
								<p>相關照片:
									<c:forEach var="picture" items="${announcement.announcementPicture}">
										<img src="${contextRoot}/relatedDownload/${picture.pictureNameMany}"
											style="max-width:300px; max-height: 300px "
											onerror="this.src='${contextRoot}/download/未上傳圖片.png'">
									</c:forEach>
								</p>
							</div>

							<!-- end -->
						</div>
					</div>
				</div>
			</body>

			</body>

			</html>