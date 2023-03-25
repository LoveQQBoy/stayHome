<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Insert title here</title>


            </head>

            <body>

                <!-- ======= Footer ======= -->
                <footer id="footer">

                    <div class="footer-top">
                        <div class="container">
                            <div class="row">

                                <div class="col-lg-4 col-md-6 footer-newsletter">
                                    <h4>關於我們</h4>
                                    <p>台灣大樓林立，社會公宅興建，經
                                        由這些現象，發想出建立小而精美
                                        的社區管理系統，藉由這系統​使社
                                        區生活更便利，並結合社區力量，
                                        提升鄰里關係。
                                    </p>

                                </div>

                                <div class="offset-lg-1 col-lg-2 col-md-6 footer-links">
                                    <h4>常用連結</h4>
                                    <ul>
                                        <li><i class="bx bx-chevron-right"></i> <a href="#">首頁</a></li>
                                        <li><i class="bx bx-chevron-right"></i> <a
                                                href="${contextRoot}/announcement/announcementRead">社區公告</a></li>
                                        <li><i class="bx bx-chevron-right"></i> <a
                                                href="${contextRoot}/vote/voteFrontPage">投票</a></li>
                                        <li><i class="bx bx-chevron-right"></i> <a
                                                href="${contextRoot}/manageCost/manageCostPageFront">管理費</a></li>
                                        <li><i class="bx bx-chevron-right"></i> <a
                                                href="${contextRoot}/repair/residentList">維修回報</a></li>
                                    </ul>
                                </div>


                                <div class="col-lg-3 col-md-6 footer-contact">
                                    <h3>宅在家</h3>
                                    <p>
                                        <i class="fa-solid fa-house"></i>
                                        高雄市中正四路211號
                                        <br><br>
                                        <i class="fa-solid fa-phone"></i> (07) 9699-885<br><br>
                                        <i class="fa-solid fa-envelope"></i>info@example.com<br>
                                    </p>
                                </div>

                                <div class="col-lg-2 col-md-6">
                                    <iframe
                                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1033.4184517400138!2d120.29181259112733!3d22.628254128221982!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x346e047cb59c5ef1%3A0xeb0d3d3bee586e78!2z6LOH562W5pyDLeaVuOS9jeaVmeiCsueglOeptuaJgC3ljZfljYDoqJPnt7TkuK3lv4Mo6auY6ZuEKQ!5e0!3m2!1szh-TW!2stw!4v1679471853358!5m2!1szh-TW!2stw"
                                        width="200" height="200" style="border:0;" allowfullscreen="" loading="lazy"
                                        referrerpolicy="no-referrer-when-downgrade"></iframe>
                                </div>

                            </div>
                        </div>
                    </div>


                </footer><!-- End Footer -->

                <div id="preloader"></div>
                <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                        class="bi bi-arrow-up-short"></i></a>


            </body>

            </html>