<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%--需要裝兩個檔案才不會出現錯誤 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.myfavorite.model.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
String location = (request.getQueryString() == null) ? request.getRequestURL().toString()
  : request.getRequestURL().append("?").append(request.getQueryString()).toString();
request.getSession().setAttribute("location", location);

List<MyFavoriteVO> list = null;

if (request.getSession().getAttribute("mem") != null) {
 list = new MyFavoriteService().getAll().stream()
 .filter(propVO -> propVO.getMemberId() == ((MemVO) request.getSession().getAttribute("mem")).getMemberId())
 .collect(Collectors.toList());
 pageContext.setAttribute("list", list);
} else {
 response.sendRedirect(request.getContextPath() + "/front_end/login.jsp");
 return;
}
%>
 
<!DOCTYPE html>

<html>
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
<!--========================<!--請注意: 以下填入你要寫的東西 開始-->

<title>我的最愛</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
/* 	border: 2px solid black; */
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
 	width: 1000px;	/*修改為1000px */
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

<!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> -->
	<table id="table-1">
		<tr>
			<td>
				<h3>我的收藏</h3>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>會員編號</th>
			<th>產品編號</th>
			<th>產品名稱</th>

			<th>移除</th>									
		</tr>
		
 <%@ include file="page1.file" %> 
 <c:forEach var="myFavoriteVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${myFavoriteVO.memberId}</td>
				<td>${myFavoriteVO.productId}</td>
				<td>${myFavoriteVO.getTheShopProd().productName}</td>
				
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/myfavorite/myfavorite.do" style="margin-bottom: 0px;">
			     <input type="submit" value="delete">
 			     <!--因為條件是memberId和memberId所以也需要傳入memberId和memberId才抓的到 -->
			     <input type="hidden" name="memberId"  value="${myFavoriteVO.memberId}">
			     <input type="hidden" name="productId"  value="${myFavoriteVO.productId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>
	
<!--*************************<!--請注意: 以上填入你要寫的東西 結束*************************-->
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