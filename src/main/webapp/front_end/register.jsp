<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>MuMu - 會員註冊 - Sign up</title>
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
    

       <!-- login Area Strat-->
         <div class="login-area pt-120 pb-120">
             <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="basic-login">                        
                            <h3 class="text-center mb-60">註冊會員</h3>
                            
                            <form method="post" action="<%=request.getContextPath()%>/front_end/member.do">
                                
                                <label for="account"> 帳號 <span>**</span></label><div style="color:red">${errorMsgs.memberAccount}</div>
                                <input id="account" name="memberAccount" value="${param.memberAccount}" type="text" placeholder="可以使用英文、數字、和底線_，且長度在6到20之間" />
                                
                                <label for="inputPassword"> 密碼 <span>**</span></label><div style="color:red">${errorMsgs.memberPassword}</div>
                                <input id="inputPassword" name="memberPassword" value="${param.memberPassword}" type="password" placeholder="可以使用英文、數字，且長度在6到20之間"/>
                                
                                <label for="ConfirmPassword"> 確認密碼 <span>**</span></label><div style="color:red">${errorMsgs.memberPassword2}</div>
                                <input id="ConfirmPassword" name="memberPassword2" value="${param.memberPassword2}" type="password" placeholder="再次輸入密碼" />
                                
                                <label for="name"> 姓名 <span>**</span></label><div style="color:red">${errorMsgs.memberName}</div>
                                <input id="name" name="memberName" value="${param.memberName}" type="text" placeholder="請輸入姓名" />
                                
                                <label for="nickname"> 暱稱 </label><div style="color:red">${errorMsgs.memberNickname}</div>
                                <input id="nickname" name="memberNickname" value="${param.memberNickname}" type="text" placeholder="請輸入暱稱  如不輸入將預設為帳號" />
                                
                                <label for="gender"> 性別 <span>**</span></label><div style="color:red">${errorMsgs.gender}</div>
                                <div class="login-action mb-20 fix">
                                    <span class="log-rem" style=" margin: 20px">
                                        <input id="male" type="radio" name="gender" value="男" ${(param.gender == "男")? "checked" : ""}/>
                                        <label for="male">男</label>
                                    </span>
                                    <span class="log-rem" style=" margin: 20px">
                                        <input id="female" type="radio" name="gender" value="女" ${(param.gender == "女")? "checked" : ""}/>
                                        <label for="female">女</label>
                                    </span>
                                </div>
                                
                                <label for="birthday"> 生日 <span>**</span></label><div style="color:red">${errorMsgs.dateOfBirth}</div>
                                <input id="birthday" name="dateOfBirth" value="${param.dateOfBirth}" type="date" min="1900-01-01"  />
                                
                                <label for="email">信箱 <span>**</span></label><div style="color:red">${errorMsgs.memberEmail}</div>
                                <input id="emali" name="memberEmail" value="${param.memberEmail}" typ="text"  placeholder="請輸入信箱" />
                                
                                <label for="phone"> 手機號碼 <span>**</span></label><div style="color:red">${errorMsgs.memberPhone}</div>
                                <input id="phone" name="memberPhone" value="${param.memberPhone}" type="text" maxlength="10" placeholder="09開頭十位數字不含其他字元 號碼格式: 09XXXXXXXX"/>
                                
                                <label for="phone"> 身分證字號 <span>**</span></label><div style="color:red">${errorMsgs.idNumber}</div>
                                <input id="phone" name="idNumber" value="${param.idNumber}" type="text" placeholder="首位英文字母請大寫" />
                                
                                <label for="adress"> 通訊地址 <span>**</span></label><div style="color:red"><span>${errorMsgs.address1}</span><span>${errorMsgs.address2}</span><span>${errorMsgs.address3}</span></div>            
                                	<span>請選擇縣市</span> <select name="address1"  id="college-list" onchange="changeCollege(this.selectedIndex)"></select>                      
        							<span>區域/鄉鎮</sapn> <select name="address2"  id="sector-list"></select>                
        						<input id="adress" name="address3" value="${param.address3}" type="text"   placeholder="請輸入街道名稱及門牌號碼" />                                  
                                
                                <div class="mt-10"></div>
                                <input type="hidden" name="action" value="insert">
        						<button class="btn btn-black w-100">註冊</button>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
            <!--請注意: 以上填入你要寫的東西 結束-->
        </div>
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
	
<!-- 地址下拉選單 -->
	 <script src="<%=request.getContextPath()%>/front_end/assets/js/mumu_mem/double_address.js"></script>
<!-- 地址下拉選單 -->
	
<!-- 日期選擇限制到今天 -->
	<script>
	    birthday.max = new Date().toISOString().split("T")[0];
	</script>
<!-- 日期選擇限制到今天 -->
</body>

</html>