<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      <c:set var="contextRoot" value="${pageContext.request.contextPath}" />

      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>登入</title>

        
      </head>

      <body>
        
		<jsp:include page="../layout/navbar.jsp"></jsp:include>
        <!-- ======= Breadcrumbs ======= -->
        <div class="breadcrumbs">
          <div class="container">
            <h2>會員登入</h2>
          </div>
        </div>
        <!-- End Breadcrumbs -->

        <!-- 登入 -->
        <div class="container w-75">
          <div class="row row-cols-1">
            <div class="col my-5 mx-auto" data-aos="fade-up-right" data-aos-delay="100">
              <form:form action="${contextRoot}/household/login" modelAttribute="householdData" method="post" autocomplete="off">
                <div class="mb-3">
                  <label for="email" class="form-label">E-mail帳號</label>
                  <form:input path="email" type="email" class="form-control" id="email" aria-describedby="emailHelp" autocomplete="off"/>
                  <div id="emailHelp" class="form-text">不要打錯唷</div>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">密碼</label>
                  <form:input path="password" type="password" class="form-control" id="password" />
                </div>
                <button type="submit" class="btn btn-outline-success">登入</button>
              </form:form>
            </div>
          </div>
        </div>
        <!-- 登入 -->

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