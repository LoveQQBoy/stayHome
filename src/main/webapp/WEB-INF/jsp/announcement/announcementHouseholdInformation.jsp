<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                <c:set var="contextRoot" value="${pageContext.request.contextPath }" />
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <meta content="width=device-width, initial-scale=1.0" name="viewport">
                    <title>公告資訊</title>
                    <style>
                        .container-background-color {
                            background-color: #f6f7f6;
                            border-radius: 20px;
                            margin-top: 10px;
                            padding-top: 10px;
                        }
                    </style>
                </head>

                <body>
                    <jsp:include page="../layout/navbar.jsp"></jsp:include>

                    <!-- ======= Breadcrumbs ======= -->
                    <div class="breadcrumbs">
                        <div class="container">
                            <h2>詳細公告</h2>
                            <p>社區公告都在這裡!
                            </p>
                        </div>
                    </div><!-- End Breadcrumbs -->



                    <!-- 線 -->
                    <div class="container w-75">
                        <div class="row row-cols-1">
                            <div class="col mt-5">
                                發佈日期:&nbsp;
                                <fmt:formatDate value="${announcement.announcementDate}" pattern="yyyy-MM-dd HH:mm" />
                            </div>
                        </div>
                    </div>
                    <div class="container w-75">
                        <div class="progress my-4 w-100" style="height: 3px;">
                            <div class="progress-bar" role="progressbar" aria-label="Example 1px high"
                                style="width: 10%; background-color: #5fcf80;" aria-valuenow="25" aria-valuemin="0"
                                aria-valuemax="100">
                            </div>
                        </div>
                    </div>
                    <!-- 線 -->

                    <main id="main">
                        <!-- picture -->
                        <div class="container w-75" data-aos="fade-up">
                            <div class="row" data-aos="zoom-in" data-aos-delay="100">
                                <div class=" mt-5 d-flex justify-content-center">
                                    <img src="${contextRoot}/download/${announcement.pictureName}"
                                        style="max-width:500px; max-height: 500px;object-fit: cover;"
                                        class="w-100 img-thumbnail">
                                </div>
                            </div>
                        </div>
                        <!-- picture-end -->

                        <!-- 公告 -->
                        <div class="container w-75  my-3">
                            <div class="row ">
                                <div class="col-12 container-background-color">
                                    <h4>公告名:${announcement.title}</h4>
                                    <p>主旨:${announcement.text}</p>
                                </div>
                            </div>
                        </div>
                        <!-- 公告end -->
                        <!-- 相關照片 -->
                        <div class="container w-75">
                            <div class="progress my-5 w-100" style="height: 3px;">
                                <div class="progress-bar" role="progressbar" aria-label="Example 1px high"
                                    style="width: 10%; background-color: #5fcf80;" aria-valuenow="25" aria-valuemin="0"
                                    aria-valuemax="100">
                                </div>
                            </div>
                        </div>
                        <div class="container w-75  my-3">
                            <div class="row ">
                                <h2>相關照片</h2>
                                <div class="col-3">
                                    <img src="${contextRoot}/download/${announcement.pictureName}"
                                        class="w-100 border border-secondary">
                                </div>
                                <c:forEach var="picture" items="${announcement.announcementPicture}">
                                    <div class="col-3">
                                        <img src="${contextRoot}/relatedDownload/${picture.pictureNameMany}" style=""
                                            class="w-100 border border-secondary"
                                            onerror="this.src='${contextRoot}/download/未上傳圖片.png'">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- 相關照片end -->

                        <!-- 留言區瀏覽 -->
                        <div class="container w-75 message-top">
                            <div class="progress m-3" style="height: 3px;">
                                <div class=" progress-bar" role="progressbar" aria-label="Basic example"
                                    style="width: 100%; background-color: #5fcf80;" aria-valuenow="100"
                                    aria-valuemin="0" aria-valuemax="100">
                                </div>
                            </div>
                            <div class="row">
                                <div class="offset-1 col-10">
                                    <!-- 線 -->
                                    <!-- 線結束 -->
                                    <div class="d-flex justify-content-center mb-3">
                                        <h3>留言區</h3>
                                    </div>
                                    <c:forEach var="page" items="${page.content}">

                                        <div>
                                            <h5>${page.householdData.name} &nbsp; <span
                                                    style="font-size: 10px; color: #828882;">
                                                    <fmt:formatDate value="${page.messageDate}"
                                                        pattern="yyyy-MM-dd HH:mm" />
                                                </span>
                                            </h5>
                                        </div>
                                        <div class="mb-5">
                                            <p>${page.messageResponse}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <!-- 留言區瀏覽結束 -->

                        <!-- page -->
                        <div class="container w-75">
                            <div class="row">
                                <figure class="text-center">
                                    <blockquote class="blockquote ">
                                        <c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
                                            <c:choose>
                                                <c:when test="${pageNumber != page.number+1}">
                                                    <a class="btn btn-outline-success"
                                                        href="${contextRoot}/announcement/announcementRead/${announcement.p_Id }?pageNumber=${pageNumber}">${pageNumber}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="btn btn-success">${pageNumber}</div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </blockquote>
                                </figure>
                            </div>
                        </div>
                        <!-- page end -->

                        <!-- 留言區發送 -->
                        <div class="container w-75">
                            <div class="row">
                                <div class="offset-1 col-10">
                                    <form method="post" action="${contextRoot}/message">
                                        <div class="input-group my-2">
                                            <span class="input-group-text">留言發送</span>
                                            <textarea class="form-control form-control-sm" aria-label="With textarea"
                                                name="messageResponse"></textarea>
                                        </div>
                                        <div class="d-flex justify-content-end">
                                            <a href="${contextRoot}/announcement/announcementRead/${announcement.p_Id}"
                                                style="margin-top: 8px;">取消</a>
                                            <input type="hidden" name="messageDate" id="messageDate">
                                            <input type="hidden" name="responseId" id="responseId"
                                                value="${announcement.p_Id }">
                                            <button type="submit" class="get-started-btn2">送出</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- 留言區發送結束 -->
                    </main>
                    <!-- footer -->
                    <jsp:include page="../layout/footer.jsp"></jsp:include>


                    <script src="${contextRoot}\js\jquery-3.6.3.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
                    <!-- Vendor JS Files -->
                    <script src="${contextRoot}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
                    <script src="${contextRoot}/assets/vendor/aos/aos.js"></script>
                    <script src="${contextRoot}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
                    <script src="${contextRoot}/assets/vendor/swiper/swiper-bundle.min.js"></script>
                    <script src="${contextRoot}/assets/vendor/php-email-form/validate.js"></script>



                    <!-- Template Main JS File -->
                    <script src="${contextRoot}/assets/js/main.js"></script>
                    <script>
                        $("form").submit(function (e) {
                            //e.preventDefault();
                            let applicantCreateDate = new Date();
                            let formattedApplicantCreateDate = moment(applicantCreateDate).format('YYYY-MM-DD HH:mm:ss');
                            console.log(formattedApplicantCreateDate);
                            $("#messageDate").attr("value", formattedApplicantCreateDate)


                            let applicantDeadDate = $("input[name='applicantDeadDateString']").val();
                            let deadDate = new Date(applicantDeadDate);
                            let formatteddeadDate = moment(deadDate).format('YYYY-MM-DD HH:mm:ss');
                            console.log(formatteddeadDate)
                            $("input[name='applicantDeadDate']").val(formatteddeadDate).trigger("change");

                        })
                    </script>

                </body>

                </html>