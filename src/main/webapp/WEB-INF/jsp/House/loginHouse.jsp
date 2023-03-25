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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="${contextRoot}/css/login.css">

		<style type="text/css">
		input:-webkit-autofill,
		input:-webkit-autofill:hover,
		input:-webkit-autofill:focus,
		input:-webkit-autofill:active
		{
		-webkit-box-shadow: 0 0 0px 1000px rgba(192,192,192,0) inset; /*淡淡的白*/
		transition: background-color 1000s ease-in-out 0s; /*透明*/
		-webkit-text-fill-color: #fff; /*字體顏色*/
		}
</style>
      </head>
      

      <body>


        <jsp:include page="../layout/navbar.jsp"></jsp:include>
        <!-- ======= Breadcrumbs ======= -->

        <!-- End Breadcrumbs -->

        <!-- 登入 -->

        <!--  -->

        <section>
          <div class="form-box">
            <div class="form-value">
              <form:form action="${contextRoot}/household/login" modelAttribute="householdData" method="post" autocomplete="off">
                <h2>會員登入</h2>
                <div class="inputbox">
                
                  <form:input type="email" id="email" path="email" autocomplete="off"   />
                  <label for="email">信箱</label>
                </div>
                <div class="inputbox">
                  <form:input type="password" placeholder="" path="password" id="password" />
                  <label for="">密碼</label>
                </div>

                <button type="submit">登入</button>
                <div class="register">
                  <p>沒有帳號嗎?<a href="${contextRoot}/household/add">註冊</a></p>
                </div>
              </form:form>
            </div>
          </div>
        </section>
        <!-- 登入 -->

        <!-- Vendor JS Files -->
        <script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
        <script src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="${contextRoot}/assets/js/main.js"></script>

        <!-- sweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!--  ionicons -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

        <c:if test="${not empty success}">
          <script>
            Swal.fire({
              icon: 'success',
              title: '註冊成功',
              showConfirmButton: false,
              timer: 1500
            });
          </script>
        </c:if>

        <c:if test="${not empty error}">
          <script>
            /* 判斷是否有錯誤訊息，有的話顯示SweetAlert提示框 */
            Swal.fire({
              icon: 'error',
              title: '帳號or密碼錯誤，請重新輸入',
              confirmButtonText: 'OK'
            })
          </script>
        </c:if>







      </body>

      </html>