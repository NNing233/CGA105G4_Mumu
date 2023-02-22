<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--需要裝兩個檔案才不會出現錯誤 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>

<%
    String location = (request.getQueryString()==null)? request.getRequestURL().toString() :request.getRequestURL().append("?").append(request.getQueryString()).toString();
    HttpSession session1 = request.getSession();
    session1.setAttribute("location", location);
%>
<!DOCTYPE html>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
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
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單資料修改</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #E6E6FA;
/*     border: 2px solid black; */
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>   
    
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

        <section class="grey-bg pt-120 pb-120">
            <div class="container">
<!--============================請注意: 以下填入你要寫的東西 開始-->

<table id="table-1">
	<tr><td>
		 <h3>訂單資料修改</h3>
	</td></tr>
</table>


<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="order.do" name="form1">
<table>
    <tr>
  <td>訂單編號:</td>
  <td>${orderVO.orderId}</td>
 </tr>
 <tr>
  <td>會員編號:</td>
  <td>${orderVO.memberId}</td>
 </tr>
 <tr>
  <td>收貨地址:<font color=red><b>*</b></font></td>
  <td><input type="TEXT" name="orderAddress" size="45"
    value="${orderVO.orderAddress}""/></td><td>${errorMsgs.orderAddress}</td>
 </tr>
 <tr>
  <td>訂單狀態:</td>
  <td>${orderVO.orderStatus}</td>
 </tr>
 <tr>
  <td>付款方式:</td>
  <td>${orderVO.orderPayment}</td>
 </tr>
 <tr>
  <td>物流方式:</td>
  <td>${orderVO.orderDelivery}</td>
 </tr> 
 <tr>
  <td>總價:</td>
  <td>${orderVO.orderTotalAmount}</td>
 </tr>
 <tr>
  <td>優惠券折扣金額:</td>
  <td>${orderVO.orderDiscount}</td>
 </tr> 
 <tr>
  <td>下單時間:</td>
  <td>${orderVO.orderTime}</td>
 </tr>
 <tr>
  <td>實付:</td>
  <td>${orderVO.orderAfterDiscount}</td>
 </tr>  
 </table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="orderId" value="${orderVO.orderId}">
<input type="submit" value="送出修改"></FORM>	


<!--============================請注意: 以上填入你要寫的東西 結束-->
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
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${orderVO.orderTime}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>        
</html>