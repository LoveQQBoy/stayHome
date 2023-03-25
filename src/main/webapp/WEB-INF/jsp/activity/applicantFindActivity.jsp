<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>活動瀏覽</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->

        <link href="${contextRoot}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link
          href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="${contextRoot}/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="${contextRoot}/assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <link href="${contextRoot}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="${contextRoot}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="${contextRoot}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="${contextRoot}/assets/css/style.css" rel="stylesheet">

        <!-- fontAwsome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
        <style>
          .offcanvas-start.show {
            width: 100%;
          }
        </style>
      </head>

      <body>

        <jsp:include page="../layout/navbar.jsp"></jsp:include>

        <main id="main" data-aos="fade-in">

          <!-- ======= Breadcrumbs ======= -->
          <div class="breadcrumbs">
            <div class="container">
              <h2>活動瀏覽</h2>
              <p>定期參與活動有助於身心健康
              </p>
            </div>
          </div><!-- End Breadcrumbs -->

          <!-- 帆布 -->


          <!-- 帆布 -->
          <!-- ======= Courses Section ======= -->
          <section id="courses" class="courses">
            <div class="container container-body" data-aos="fade-up">

              <!-- 頁數+線 -->
              <figure class="">
                <blockquote class="blockquote ">
                  <div class="container">
                    <div class="row">
                      <div class="col-2">
                        <div style=" display: inline;">
                          <button class="btn btn-success" type="button" data-bs-toggle="offcanvas"
                            data-bs-target="#activityQuery" aria-controls="offcanvasWithBothOptions">條件查詢</button>
                        </div>
                      </div>
                      <div class="offset-8 col-2">
                        <div class="ms-5" style="display: inline ;">
                          <!--  判斷是否有條件搜尋 -->
                          <c:choose>
                            <c:when test="${searchPage != null}">
                              <c:forEach var="pageNumber" begin="1" end="${searchPage.totalPages}">
                                <c:choose>
                                  <c:when test="${pageNumber != searchPage.number+1}">
                                    <form action="${contextRoot}/activity/applicantFindActivity" method="get"
                                      style="display: inline ;">
                                      <input type="hidden" name="keyWord" value="${keyWord}">
                                      <input type="hidden" name="startTime" value="${startTime}">
                                      <input type="hidden" name="endTime" value="${endTime}">
                                      <input type="hidden" name="searchPageNumber" value="${pageNumber}">
                                      <input type="hidden" name="activityStatusString" value="${activityStatusString}">
                                      <button type="submit" class="get-started-circle"
                                        style="display: inline ;">${pageNumber}</button>
                                    </form>
                                  </c:when>
                                  <c:otherwise>
                                    <button type="button" class="get-started-circle-choose">${pageNumber}</button>
                                  </c:otherwise>
                                </c:choose>
                              </c:forEach>
                            </c:when>
                            <c:otherwise>
                              <!-- 沒有條件搜尋執行這 -->
                              <c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
                                <c:choose>
                                  <c:when test="${pageNumber != page.number+1}">
                                    <a class="get-started-circle"
                                      href="${contextRoot}/activity/applicantFindActivity?pageNumber=${pageNumber}">${pageNumber}</a>
                                  </c:when>
                                  <c:otherwise>
                                    <button type="button" class="get-started-circle-choose">${pageNumber}</button>
                                  </c:otherwise>
                                </c:choose>
                              </c:forEach>
                              <!-- 沒有條件搜尋執行這 -->
                            </c:otherwise>
                          </c:choose>
                          <!--  判斷是否有條件搜尋 -->
                        </div>
                      </div>

                    </div>
                  </div>

                </blockquote>
              </figure>
              <div class="progress mb-5 " style="height: 2px;">
                <div class="progress-bar" role="progressbar" aria-label="Example 1px high"
                  style="width: 10%;background-color: #5fcf80;" aria-valuenow="25" aria-valuemin="0"
                  aria-valuemax="100">
                </div>
              </div>
              <!-- 頁數+線 -->
              <div class="row" data-aos="zoom-in" data-aos-delay="100">
                <!-- 條件查詢 -->
                <c:choose>
                  <c:when test="${searchPage != null}">
                    <!-- 條件查詢foreach -->
                    <c:forEach items="${searchPage.content}" var="page">
                      <div class="col-lg-4  col-md-6 px-5 my-4">
                        <div class="course-item">
                          <img src="${contextRoot}/activity/activityPicture?activityId=${page.p_Id}" class="w-100"
                            style="object-fit: cover;height: 350px;width: 350px;">
                          <div class="course-content">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                              <h4><i class="bx bx-user"></i>&nbsp;${page.applicantNumber}/${page.applicantNumberFull}
                              </h4>
                            </div>
                            <h3><a
                                href="${contextRoot}/activity/applicantFindActivityInformation?activityId=${page.p_Id}">活動名:${page.title}</a>
                            </h3>
                            <p>活動狀態:${page.applicantStatus}</p>
                            <div class="trainer d-flex justify-content-between align-items-center">
                              <div class="trainer-profile d-flex align-items-center">
                              </div>
                              <div class="container-fluid p-0">
                                <div class="row">
                                  <div class="col-9">
                                    截止時間:
                                    <fmt:formatDate value="${page.applicantDeadDate}" pattern="yyyy-MM-dd HH:mm" />
                                  </div>
                                  <div class="col-3">
                                    <i class="fa-solid fa-eye"></i>&nbsp;${page.count}
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div> <!-- End Course Item-->
                    </c:forEach>
                    <!-- 條件查詢foreach -->
                  </c:when>
                  <c:otherwise>
                    <!-- forEach 非條件查詢建立多個活動 -->
                    <c:forEach items="${page.content}" var="page">
                      <div class="col-lg-4  col-md-6 px-5 my-4">
                        <div class="course-item">
                          <img src="${contextRoot}/activity/activityPicture?activityId=${page.p_Id}" class="w-100"
                            style="object-fit: cover;height: 350px;width: 350px;">
                          <div class="course-content">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                              <h4><i class="bx bx-user"></i>&nbsp;${page.applicantNumber}/${page.applicantNumberFull}
                              </h4>
                            </div>
                            <h3><a
                                href="${contextRoot}/activity/applicantFindActivityInformation?activityId=${page.p_Id}">活動名:${page.title}</a>
                            </h3>
                            <p>活動狀態:${page.applicantStatus}</p>
                            <div class="trainer d-flex justify-content-between align-items-center">
                              <div class="trainer-profile d-flex align-items-center">
                              </div>
                              <div class="container-fluid p-0">
                                <div class="row">
                                  <div class="col-9">
                                    截止時間:
                                    <fmt:formatDate value="${page.applicantDeadDate}" pattern="yyyy-MM-dd HH:mm" />
                                  </div>
                                  <div class="col-3">
                                    <i class="fa-solid fa-eye"></i>&nbsp;${page.count}
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div> <!-- End Course Item-->
                    </c:forEach>
                    <!-- forEach 非條件查詢建立多個活動 -->
                  </c:otherwise>
                </c:choose>
                <!-- 條件查詢 -->
              </div>
            </div>
          </section><!-- End Courses Section -->




        </main><!-- End #main -->
        <!-- 帆布 -->

        <div class="offcanvas offcanvas-start " tabindex="-1" id="activityQuery" aria-labelledby="activityQueryLabel">
          <div class="offcanvas-header">
            <h2 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">查詢種類
            </h2>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body">
            <div class="container">
              <div class="row">
                <form action="${contextRoot}/activity/findPageByTimeAndString" method="post">
                  <div class="mb-5">
                    <label for="queryByKeypoint" class="form-label">
                      <h3>關鍵字查詢</h3>
                    </label>
                    <input type="text" class="form-control" id="keyWord" name="keyWord" aria-describedby="emailHelp">
                  </div>
                  <div class="mb-5">
                    <label for="queryByTimeFrom" class="form-label">
                      <h3>時間查詢</h3>
                    </label>
                    <input type="date" class="form-control" id="startTime" name="startTime" id="startTime">
                    <label for="queryByTimeAfer" class="form-label mt-2">至</label>
                    <input type="date" class="form-control" name="endTime" id="endTime">
                  </div>
                  <h3>狀態查詢</h3>
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="activityStatus" id="activityStatus1" value="未額滿">
                    <label class="form-check-label" for="activityStatus1">
                      未額滿
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="activityStatus" id="activityStatus2" value="已額滿">
                    <label class="form-check-label" for="activityStatus2">
                      已額滿
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="activityStatus" id="activityStatus3" value="已截止">
                    <label class="form-check-label" for="activityStatus3">
                      已截止
                    </label>
                  </div>
                  <br><br>
                  <button type="submit" class="btn btn-primary">查詢</button>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- 帆布 -->

        <script>
          window.onload = function () {
            showMessage();
          };

          function showMessage() {
            let message = "${message}";
            if (message == "你已經報名過了") {
              Swal.fire(
                '非常抱歉',
                '你已經重複報名過了',
                'error'
              )
            } else if (message == "報名成功") {
              Swal.fire(
                '恭喜您',
                '報名成功',
                'success'
              )
            } else if (message == "活動已額滿") {
              Swal.fire(
                '非常抱歉',
                '活動已額滿',
                'error'
              )
            }
          }
          let startDate = document.getElementById("startTime");
          let endDate = document.getElementById("endTime");

          //註冊時間卡控
          startDate.addEventListener('change', function () {
            console.log(endDate.value)
            //startDate>endDate
            if (moment(startDate.value).isAfter(endDate.value)) {
              Swal.fire({
                icon: 'warning',
                title: '開始時間不能超過結束時間',
                showConfirmButton: false,
                timer: 2000
              })
            }
          })

          endDate.addEventListener('change', function () {
            if (moment(endDate.value).isBefore(startDate.value)) {
              Swal.fire({
                icon: 'warning',
                title: '結束時間不能小於開始時間',
                showConfirmButton: false,
                timer: 2000
              })

            }
          })
          //註冊時間卡控

        </script>

        <!-- footer -->
        <jsp:include page="../layout/footer.jsp"></jsp:include>

        <!-- Vendor JS Files -->
        <script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>


        <!-- Template Main JS File -->
        <script src="${contextRoot}/assets/js/main.js"></script>

        <!-- sweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- moment.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
      </body>

      </html>