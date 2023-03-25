<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <c:set var="contextRoot" value="${pageContext.request.contextPath }" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>公告資訊</title>
		 <meta content="" name="description">
         <meta content="" name="keywords">

      </head>

      <body>
        <jsp:include page="../layout/navbar.jsp"></jsp:include>

        <main id="main">
          <!-- ======= Breadcrumbs ======= -->
          <div class="breadcrumbs">
            <div class="container">
              <h2>錯誤頁面</h2>
              <p>哎呀這個網頁出了點小問題
              </p>
            </div>
          </div>
          <!-- End Breadcrumbs -->

			<div class="container container-body" >
				<div class="row " style="margin-top:200px">
					<div id="">
					<h1 class="text-center">抱歉這個網頁出了點小問題,請稍後再嘗試</h1>
					
					</div>
				</div>
			</div>
         
          <!-- Courses Section -->
        </main>


        <!-- footer -->
        <jsp:include page="../layout/footer.jsp"></jsp:include>
        <!-- Vendor JS Files -->
        <script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
        <script src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="${contextRoot}/assets/js/main.js"></script>
      </body>

      </html>