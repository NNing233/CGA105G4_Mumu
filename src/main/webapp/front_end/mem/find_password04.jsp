<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Bakix - Crowdfunding Startup Fundraising HTML5 Template</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="manifest" href="site.webmanifest">
    <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/front_end/assets/img/favicon.png">
    <!-- Place favicon.png in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/animate.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/meanmenu.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/slick.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/responsive.css">


    <script src="https://kit.fontawesome.com/fb61cff1c9.js" crossorigin="anonymous"></script>
    <!--請注意: 要引入 fontawesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/scss/main.css">
    <!--請注意: 可寫css 在main.css內 -->
<!-- 另外加的外連css     -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/mumu_mem/find_password.css">
<!-- 另外加的外連css     -->

</head>

<body>
    <!-- preloader -->
    <div id="preloader">
        <div class="preloader">
            <span></span>
            <span></span>
        </div>
    </div>
    <!-- preloader end  -->

   <%@ include file="/front_end/header/header.jsp"%>

    <main>

        <section class="white-bg pt-120 pb-120">
            <!--請注意: 以下填入你要寫的東西 開始-->
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-11 col-sm-9 col-md-7 col-lg-6 col-xl-5 text-center p-0 mt-3 mb-2">
                        <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                            <h2 id="heading">忘記密碼</h2>
                            <div id="msform">

                                <!-- progressbar -->
                                <ul id="progressbar">
                                    <li class="active" id="account"><strong>發送驗證碼</strong></li>
                                    <li class="active" id="personal"><strong>接收驗證碼</strong></li>
                                    <li class="active" id="payment"><strong>修改密碼</strong></li>
                                    <li class="active" id="confirm"><strong>成功</strong></li>
                                </ul>

                                <br>
                                <!-- fieldsets -->
                                    <div class="form-card">
                                        <h2 class="purple-text text-center"><strong>密碼修改完成!</strong></h2>
                                        <div class="row justify-content-center">
                                            <div class="col-7 text-center">
                                                <h5 class="purple-text text-center">請使用新密碼登入!</h5>
                                            </div>
                                        </div>
                                    </div>
                                   
                                     <a href="<%=request.getContextPath() %>/front_end/login.jsp" type="hidden">
                                     <button class="btn w-100">重新登入</button>
                                     </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->


    </main>
    <footer data-background="<%=request.getContextPath()%>/front_end/assets/img/bg/footer.jpg">

        <div class="footer-area pt-5">
            <div class="container">
                <div class="row">
                    <div class="col-xl-2 col-lg-3 col-md-4">
                        <div class="footer-widget mb-40">
                            <div class="footer-logo mb-25">
                                <img src="<%=request.getContextPath()%>/front_end/assets/img/logo/Mumu_logo.png" alt="">
                            </div>
                            <div class="social-icon">
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-behance"></i></a>
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-youtube"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-2 col-lg-2 col-md-4">
                        <div class="footer-widget mb-40">
                            <h4 class="footer-title">募募 Mumu</h4>
                            <ul class="footer-link">
                                <li><a href="#">關於我們</a></li>

                            </ul>
                        </div>
                    </div>
                    <div class="col-xl-2 col-lg-3 col-md-4">
                        <div class="footer-widget mb-40">
                            <h4 class="footer-title">如何使用</h4>
                            <ul class="footer-link">
                                <li><a href="#">網站使用條款</a></li>


                            </ul>
                        </div>
                    </div>

                    <div class="col-xl-2 col-lg-3 col-md-4">
                        <div class="footer-widget mb-40">
                            <h4 class="footer-title">常見問題</h4>
                            <ul class="footer-link">
                                <li><a href="#">問答集</a></li>
                                <li><a href="#">- 如何發起一個募資</a></li>
                                <li><a href="#">- 如何贊助他人的專案</a></li>
                                <li><a href="#">- 如何發起許願</a></li>
                            </ul>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <div class="copyright-area">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="copyright-text text-center">
                            <p>Copyright All Right Reserved - 2022</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>


    <!-- JS here -->
    <script src="<%=request.getContextPath()%>/front_end/assets/js/vendor/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/isotope.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/instafeed.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/slick.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/jquery.meanmenu.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/ajax-form.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/wow.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/jquery.scrollUp.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/imagesloaded.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/jquery.magnific-popup.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/plugins.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/assets/js/main.js"></script>

    <!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
    <script src="<%=request.getContextPath()%>/front_end/assets/js/dropdown.js"></script>
</body>

</html>