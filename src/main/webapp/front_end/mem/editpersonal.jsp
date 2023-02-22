<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.mem}">
	<jsp:forward page="../login.jsp"/>
</c:if>








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
<!-- 新增css-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/css/mumu_mem/personalpage.css">
<!-- 新增css-->
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
   <!-- 這裡開始    -->
        <div class="container rounded bg-white mt-5 mb-5">
        
            <div class="row">
              <form class="row" method="post" action="<%=request.getContextPath()%>/front_end/mem/member.do" enctype="multipart/form-data" >
                <div class="col-md-4 border-right">
                
                    <div id='previewMultiple' class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5"  src="<%=request.getContextPath()%>/front_end/mem/photo?memberId=${sessionScope.mem.memberId}" style="width: 200px;height: 200px;">
                    	<span class="font-weight-bold">${sessionScope.mem.memberNickname}</span>
                    </div>
                    <div class="mt-5 text-center">
                    	<label class="btn btn-primary profile-button">
	                    	<input id="showimg" style="display:none;" name="upFiles" type="file"  multiple="multiple" />
	                        選擇圖片
                        </label>
                     </div>
                     
                
                </div>

                <div class="col-md-7 ">
                
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">修改資料</h4>
                        </div>
                                       
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels">姓名</label><div style="color:red">${errorMsgs.memberName}</div>
                                <input type="text" class="form-control" name="memberName" value="${param.memberName}">
                            </div>
                            <div class="col-md-12">
                                <label class="labels">暱稱</label><div style="color:red">${errorMsgs.memberNickname}</div>
                                <input type="text" class="form-control" name="memberNickname" value="${param.memberNickname}">
                            </div>
                            <div class="col-md-12">
                                <label for="gender"> 性別 </label><div style="color:red">${errorMsgs.gender}</div>
                                <div class="login-action mb-20 fix">
                                    <span class="log-rem" style=" margin: 20px">
                                        <input id="male" type="radio" name="gender"  value="男" ${(param.memberGender == "男")? 'checked':''} />
                                        <label for="male">男</label>
                                    </span>
                                    <span class="log-rem" style=" margin: 20px">
                                        <input id="female" type="radio" name="gender" value="女" ${(param.memberGender == "女")? 'checked':''}/>
                                        <label for="female">女</label>
                                    </span>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label class="labels">生日</label><div style="color:red">${errorMsgs.dateOfBirth}</div>
                                <input type="date" class="form-control" name="dateOfBirth"  value="${param.dateOfBirth}"  min="1900-01-01"">
                            </div>
                            <div class="col-md-12">
                                <label class="labels">信箱</label><div style="color:red">${errorMsgs.memberEmail}</div>
                                <input type="text" class="form-control" name="memberEmail" value="${param.memberEmail}">
                            </div>
                            <div class="col-md-12">
                                <label class="labels">手機號碼</label><div style="color:red">${errorMsgs.memberPhone}</div>
                                <input type="text" class="form-control" name="memberPhone" value="${param.memberPhone}">
                            </div>
                            <div class="col-md-12">
                                <label class="labels">身分證字號</label><div style="color:red">${errorMsgs.idNumber}</div>
                                <input type="text" class="form-control" name="idNumber" value="${param.idNumber}">
                            </div>
                            <div class="col-md-12">
                                <label class="labels">通訊地址</label><div style="color:red">${errorMsgs.memberAddress}</div>
                                <input type="text" class="form-control" name="memberAddress" value="${param.memberAddress}">
                            </div>
                            <div class="col-md-12">
                                <label class="labels">銀行帳戶</label><div style="color:red">${errorMsgs.bankAccount}</div>
                                <input type="text" class="form-control" name="bankAccount" value="${param.bankAccount}">
                            </div>
                            
                        </div>
                        <div class="row mt-3">
                            
                        </div>
                        <div class="mt-5 text-center">
                        	<input type="hidden" name="memberId" value="${sessionScope.mem.memberId}"/>
                        	<input type="hidden" name="memberAccount" value="${sessionScope.mem.memberAccount}"/>
	                    	<input type="hidden" name="action" value="update"/>
                            <button class="btn btn-primary profile-button" type="submit">保存</button>
                        </div>
                        
                    </div>
                
                </div>
				</form>
            </div>
        
        </div>

<!-- 這裡結束 -->
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
<!-- JavaScript part -->
	<script src="<%=request.getContextPath()%>/front_end/assets/js/mumu_mem/preview.jsp"></script>
    <script>

    birthday.max = new Date().toISOString().split("T")[0];

    </script>
    
    
</body>

</html>