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
              <h2>公告瀏覽</h2>
              <p>社區公告都在這裡!
              </p>
            </div>
          </div><!-- End Breadcrumbs -->

          <!-- ======= Courses Section ======= -->
          <section id="events-card-type2" class=" events-card-type2 ">
            <div class="container container-body" data-aos="fade-up">
              <!-- 線 -->
              <figure class="">
                <blockquote class="blockquote mt-3">
                  <!-- <div style=" display: inline;">
                    <button class="btn btn-success me-5" type="button" data-bs-toggle="offcanvas"
                      data-bs-target="#announcementQuery" aria-controls="offcanvasWithBothOptions">條件查詢</button>
                  </div> -->
                  <div class="container">
                  	<div class="row">
                  		<div class="col-3 ms-auto">
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
                  </div>
                  </div>
                  </div>
                </blockquote>
              </figure>
              <div class="progress  mb-5" style="height: 2px;">
                <div class="progress-bar" role="progressbar" aria-label="Example 1px high"
                  style="width: 10%;background-color: #5fcf80;" aria-valuenow="25" aria-valuemin="0"
                  aria-valuemax="100">
                </div>
              </div>
              <!-- 線 -->
              <div class="row row-cols-1 " data-aos="zoom-in" data-aos-delay="100">
                <div class="col ">
                  <c:forEach var="page" items="${page.content}">
                    <div class="card mb-5 ">
                      <div class="row g-0">
                        <div class="col-md-4" style=" width: 250px;">
                          <a href="${contextRoot}/announcement/announcementRead/${page.p_Id}">
                            <img src="${contextRoot}/download/${page.pictureName}" alt="" class="w-100 announcement-img"
                              style="">
                          </a>
                        </div>
                        <div class="col-md-8">
                          <div class="card-body">
                            <h5 class="card-title text-start"><a
                                href="${contextRoot}/announcement/announcementRead/${page.p_Id}">${page.title}</a>
                            </h5>
                            <p class="card-text "><small class="text-muted">發布時間:
                                <fmt:formatDate value="${page.announcementDate}" pattern="yyyy-MM-dd HH:mm" />
                              </small>
                            </p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </div>
            </div>
          </section>
          <!-- Courses Section -->
        </main>

        <!-- 帆布 -->

        <!-- <div class="offcanvas offcanvas-start " tabindex="-1" id="announcementQuery"
          aria-labelledby="activityQueryLabel">
          <div class="offcanvas-header">
            <h2 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">查詢種類
            </h2>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body">
            <div class="container">
              <div class="row">
                <form action="${contextRoot}/announcement/announcementCondition" method="get">
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
                  <h3>留言人數查詢</h3>
                  <label for="lower" class="form-label">數量</label>
                  <input type="range" class="form-range" min="0" max="100" id="lower" value="0"
                    oninput="this.nextElementSibling.value = this.value" name="lower">
                  <output>0</output>
                  <br><br>
                  <button type="submit" class="btn btn-primary">查詢</button>
                </form>
              </div>
            </div>
          </div>
        </div> -->
        <!-- 帆布 -->


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