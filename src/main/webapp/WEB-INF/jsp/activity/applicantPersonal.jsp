<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>活動個人資料</title>

      </head>

      <body>
        <jsp:include page="../layout/navbar.jsp" />

        <!-- ======= Breadcrumbs ======= -->
        <div class="breadcrumbs">
          <div class="container">
            <h2>個人活動報名資訊</h2>
            <p>個人活動的報名資訊都在這喔!!
            </p>
          </div>
        </div><!-- End Breadcrumbs -->

        <section id="courses" class="courses">
          <div class="container container-body">
            <!-- 線 -->
            <div class="container" data-aos="fade-up">
              <figure class="text-end">
                <blockquote class="blockquote mt-3">
                  <c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
                    <c:choose>
                      <c:when test="${pageNumber != page.number+1}">
                        <a class="get-started-circle"
                          href="${contextRoot}/announcement/announcementRead?pageNumber=${pageNumber}">${pageNumber}</a>
                      </c:when>
                      <c:otherwise>
                       <button type="button" class="get-started-circle-choose">${pageNumber}</button>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                </blockquote>
              </figure>
              <div class="progress  mb-5" style="height: 2px;">
                <div class="progress-bar" role="progressbar" aria-label="Example 1px high"
                  style="width: 10%;background-color: #5fcf80;" aria-valuenow="25" aria-valuemin="0"
                  aria-valuemax="100">
                </div>
              </div>
            </div>
            <!-- 線 -->
            <div class="row">
              <table class="table align-middle">
                <thead>
                  <tr>
                    <th>活動名</th>
                    <th>報名時間</th>
                    <th>報名狀態</th>
                    <th>活動狀態</th>
                    <th>詳情</th>
                    <th>動作</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="page" items="${page.content}">
                    <div>
                      <tr>
                        <td>${page.activity.title}</td>
                        <td>
                          <fmt:formatDate value="${page.registrationDate}" pattern="yyyy-MM-dd HH:mm" />
                        </td>
                        <td>
                          <c:choose>
                            <c:when test="${applicantInnerJoinActivity[2] == '已取消'}">
                              <span style="color: #E63946;">${page.applicantStatus}</span>

                            </c:when>
                            <c:otherwise>
                              ${page.applicantStatus}
                            </c:otherwise>
                          </c:choose>
                        </td>
                        <td>
                          ${page.activity.applicantStatus}
                        </td>
                        <td>
                          <a
                            href="${contextRoot}/activity/applicantFindActivityInformation?activityId=${page.activity.p_Id}">活動詳情</a>
                        </td>
                        <td>
                          <c:choose>
                            <c:when test="${page.applicantStatus == '已取消'}">
                            </c:when>
                            <c:when test="${page.activity.applicantStatus == '已截止'}">
                            </c:when>
                            <c:otherwise>
                              <form action="${contextRoot}/activity/applicantCancleActivity" method="post">
                                <input name="_method" type="hidden" value="put">
                                <input name="activityId" type="hidden" value=" ${page.activity.p_Id}">
                                <button type="submit" class="btn btn-outline-success">取消報名</button>
                              </form>
                            </c:otherwise>
                          </c:choose>
                        </td>
                      </tr>
                    </div>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </section>

        <script>
          window.onload = function () {
            alert();
          }

          function alert() {
            let message = "${message}";
            if (message == "您已取消報名") {
              Swal.fire({
                title: "提醒您",
                text: "您已取消活動",
                icon: "warning",
                showConfirmButton: false,
                timer: 1500
              })
            }
          }
        </script>

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
        <!-- sweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
      </body>

      </html>