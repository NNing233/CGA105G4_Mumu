<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.mem.model.*"%>

<c:if test="${empty sessionScope.mem}">
	<jsp:forward page="../login.jsp"/>
</c:if>

<%
	

%>

<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>MuMu - 個人頁面 - Personal</title>
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
                <div class="col-md-3 border-right">
                
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    
                    <img class="rounded-circle mt-5"  src="<%=request.getContextPath()%>/front_end/mem/photo?action=show_image&memberId=${sessionScope.mem.memberId}" style="width: 200px;height: 200px;">
                        <span class="font-weight-bold">${sessionScope.mem.memberNickname}</span>
                    
                        
                    </div>
                </div>

                <div class="col-md-5 border-right">
                
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">個人頁面</h4>
                        </div>
                        
                        
                        <div class="row mt-2">
                        <table class="table">
                        	<tbody>
	                        	<tr>
	                        		<th>姓名</th>
	                        		<td>${sessionScope.mem.memberName} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>暱稱</th>
	                        		<td>${sessionScope.mem.memberNickname} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>性別</th>
	                        		<td>${sessionScope.mem.memberGender} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>生日</th>
	                        		<td>${sessionScope.mem.dateOfBirth} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>信箱</th>
	                        		<td>${sessionScope.mem.memberEmail} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>手機</th>
	                        		<td>${sessionScope.mem.memberPhone} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>身分證字號</th>
	                        		<td>${sessionScope.mem.idNumber} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>通訊地址</th>
	                        		<td>${sessionScope.mem.memberAddress} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>銀行帳戶</th>
	                        		<td>${sessionScope.mem.bankAccount} </td>
	                        	</tr>
	                        	<tr>
	                        		<th>註冊時間</th>
	                        		<td><fmt:formatDate value="${sessionScope.mem.getSignUpDatetime()}" pattern="yyyy-MM-dd HH:mm" /></td>
	                        	</tr>
	                        	<tr>
	                        		<th></th>
	                        		<td></td>
	                        	</tr>
                        	</tbody>
                          </table>
                        </div>
                        
                        <div class="mt-5 text-center">
                        	<form method="post" action="<%=request.getContextPath()%>/front_end/mem/member.do" >
	                        	<input type="hidden" name="memberId" value="${sessionScope.mem.memberId}"/>
	                        	<input type="hidden" name="action" value="getOne_For_Update"/>
	                            <button class="btn btn-primary profile-button" type="submit">編輯資料</button>
                            </form>
                        </div>
                    </div>
                
                </div>

                <div class="col-md-4">
                <form method="post" action="<%=request.getContextPath()%>/front_end/mem/member.do">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">修改密碼</h4>
                        </div>
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <span >修改成功後將會自動登出，請以新密碼重新登入。</span>
                            <span style="color:red">${requestScope.error_msg}</span>
                        </div>
                        <div class="col-md-12"><label class="labels">請輸入舊密碼</label><div style="color:red">${errorMsgs.oldPassword}</div>
                            <input  type="password" class="form-control" name="oldPassword" value="${param.oldPassword}" ></div> <br>
                        <div class="col-md-12"><label class="labels">請輸入新密碼</label><div style="color:red">${errorMsgs.newPassword}</div>
                            <input type="password" class="form-control" name="newPassword" value="${param.newPassword}" ></div>
                        <div class="col-md-12"><label class="labels">請確認新密碼</label><div style="color:red">${errorMsgs.newPassword2}</div>
                            <input type="password" class="form-control" name="newPassword2" value="${param.newPassword2}"></div>
                    </div>
                    <div class="mt-5 text-center">
                    	<input type="hidden" name="memberId" value="${sessionScope.mem.memberId}">
                    	<input type="hidden" name="action" value="change_pw">
                        <button class="btn btn-primary profile-button" type="submit">確定修改</button>
                    </div>
                </form>
                </div>
            </div>
        
        </div>
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
</body>

</html>