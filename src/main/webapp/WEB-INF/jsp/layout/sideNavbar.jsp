<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <c:set var="contextRoot" value="${pageContext.request.contextPath }" />
        <!DOCTYPE html>
        <html>

        <head>

            <meta charset="UTF-8">
            <title>宅在家後台</title>
            <!-- css連結 -->
            <link rel="stylesheet" href="${contextRoot}/css/style.css">
            <!-- JQuery -->
            <script src="${contextRoot}\js\jquery-3.6.3.min.js"></script>
            <!-- moment.js -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
            <!-- js連結 -->
            <script src="${contextRoot}/js/script.js"></script>
            <!-- fontAwesome 連結 -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
            <script src="https://kit.fontawesome.com/765cc93115.js" crossorigin="anonymous"></script>
            <!-- bootstrap -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
            <!-- aos -->
            <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
            <script src="https://unpkg.com/aos@next/dist/aos.js"></script>

        </head>

        <body>

            <!-- 左邊 -->
            <div class="container-left">
                <!-- 導覽列 -->
                <nav id="sidebar">
                    <!-- 展開/縮起來 -->
                    <button type="button" id="collapse" class="collapse-btn">
                        <i class="fa-solid fa-left-long"></i>
                    </button>

                    <!-- List列表 -->

                    <ul class="list-unstyled">
                        <h1 class="mt-2">宅在家</h1>

                        <div class="member">
                            <img height="=50" width="50" src="${contextRoot}/download/管理者頭像.png"
                                class="manager-img ms-2" style="display:inline-block;">
                            <span class="span-style">
                                ${name}&nbsp您好
                            </span>
                        </div>

                        <!-- 聖富/柏杰 -->

                        <li>
                            <a href="#household" data-bs-toggle="collapse" id="dropdown">住戶資訊 <i
                                    class="fa-solid fa-house-user"></i></a>
                            <!-- 子列表 -->
                            <ul id="household" class="list-unstyled collapse">
                                <li><a href="${contextRoot}/household/readpage">個人資訊修改</a></li>
                                <li>
                                    <a href="${contextRoot}/vote/voteBackPage">投票管理</a>
                                </li>
                                <li>
                                    <a href="${contextRoot}/manageCost/manageCostPageBack">管理費</a>
                                </li>
                            </ul>
                        </li>

                        <!-- 聖富/柏杰 -->
                        <!-- 翊綸 -->

                        <li>
                            <a href="#shopMall" data-bs-toggle="collapse" id="dropdown">商城 <i
                                    class="fa-brands fa-shopify"></i></a>
                            <!-- 子列表 -->
                            <ul id="shopMall" class="list-unstyled collapse">
                                <li>
                                    <a href="${contextRoot}/collectParcel/backendDemo">代收包裹</a>
                                </li>
                                <li>
                                    <a href="#">新增</a>
                                </li>
                            </ul>
                        </li>

                        <!-- 翊綸 -->
                        <!-- 右昕 -->

                        <li>
                            <a href="#postulate" data-bs-toggle="collapse" id="dropdown">公設 <i
                                    class="fa-solid fa-square-person-confined"></i></a>
                            <!-- 子列表 -->
                            <ul id="postulate" class="list-unstyled collapse">
                                <li>
                                    <a href="${contextRoot}/repair/backList">維修回報</a>
                                </li>
                                <li>
                                    <a href="${contextRoot}/public/appointmentListBack">預約公設</a>
                                </li>
                            </ul>
                        </li>

                        <!-- 右昕 -->
                        <!-- 嵂焜 -->

                        <li>
                            <a href="#Activity" data-bs-toggle="collapse" id="dropdown">公告 <i
                                    class="fa-solid fa-bullhorn"></i></a>
                            <!-- 子列表 -->
                            <ul id="Activity" class="list-unstyled collapse">
                                <li>
                                    <a href="${contextRoot}/announcement/announcement">公告資訊</a>
                                </li>
                                <li>
                                    <a href="${contextRoot}/activity/activityFindPage">活動資訊</a>
                                </li>
                                <li>
                                    <a href="${contextRoot}/activity/applicantFindAllPage">活動報名者瀏覽</a>
                                </li>
                            </ul>
                        </li>

                        <!-- 嵂焜 -->


                        <li>
                            <a href="#">沒再用 <i class="fa-solid fa-bullhorn"></i></a>
                        </li>
                        <li>
                            <a href="#">XX <i class="fa-solid fa-question"></i></a>
                        </li>
                        <li>
                            <a href="${contextRoot}/">返回前台 <i class="fa-solid fa-house-chimney"></i></a>
                        </li>
                    </ul>

                </nav>
            </div>
            <script>
                AOS.init();
            </script>
        </body>

        </html>