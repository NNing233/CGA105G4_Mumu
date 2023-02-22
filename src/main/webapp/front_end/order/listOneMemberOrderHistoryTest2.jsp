<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--需要裝兩個檔案才不會出現錯誤 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.stream.Collectors"%>


<%
String location = (request.getQueryString() == null) ? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
request.getSession().setAttribute("location", location);

List<OrderVO> list = null;

if (request.getSession().getAttribute("mem") != null) {
	list = new OrderService().getAll().stream()
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
<link rel="shortcut icon" type="image/x-icon"
	href="../assets/img/favicon.png">
<!-- Place favicon.png in the root directory -->

<!-- CSS here -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="../assets/css/animate.min.css">
<link rel="stylesheet" href="../assets/css/magnific-popup.css">
<link rel="stylesheet" href="../assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="../assets/css/meanmenu.css">
<link rel="stylesheet" href="../assets/css/flaticon.css">
<link rel="stylesheet" href="../assets/css/slick.css">
<link rel="stylesheet" href="../assets/css/style.css">
<link rel="stylesheet" href="../assets/css/responsive.css">


<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet" href="../assets/scss/main.css">
<!--請注意: 可寫css 在main.css內 -->
</head>
<body>
	<!-- preloader -->
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- preloader end  -->

	<%@ include file="/front_end/header/header.jsp"%>

	<main>

		<section class="grey-bg pt-120 pb-120">
			<div class="container">
				<!--========================<!--請注意: 以下填入你要寫的東西 開始-->

				<title>自己的訂單資料</title>

				<style>
table#table-1 {
	background-color: #E6E6FA;
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
	width: 1000px; /*修改為1000px */
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #a7a7d0;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

				</head>
				<body bgcolor='white'>

					<!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> -->
					<table id="table-1" class="table table-striped table-hover ">
						<tr>
							<td>
								<h3>我的訂單</h3>
							</td>
						</tr>
					</table>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>訂單編號</th>
<!-- 								<th>會員編號</th> -->
								<th>收貨地址</th>
								<th>訂單狀態</th>
								<th>付款方式</th>
								<th>物流方式</th>
								<th>總價</th>
								<th>訂單折扣金額</th>
								<th>實付</th>
								<th>下單時間</th>
								<th>修改</th>
								<th>刪除</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
									<td>${orderVO.orderId}</td>
<%-- 									<td>${orderVO.memberId}</td> --%>
									<td>${orderVO.orderAddress}</td>
									<td>${orderVO.orderStatus}</td>
									<td>${orderVO.orderPayment}</td>
									<td>${orderVO.orderDelivery}</td>
									<td>${orderVO.orderTotalAmount}</td>
									<td>${orderVO.orderDiscount}</td>
									<td>${orderVO.orderAfterDiscount}</td>
									<td>${orderVO.orderTime}</td>
									<c:if test="${orderVO.orderStatus == '待出貨'}">
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/front_end/order/order.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="orderId" value="${orderVO.orderId}">
												<input type="hidden" name="action" value="update_data">
											</FORM>
										</td>
									</c:if>

									<c:if test="${orderVO.orderStatus == '待出貨'}">
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/front_end/order/order.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="orderId" value="${orderVO.orderId}">
												<input type="hidden" name="action" value="delete">
											</FORM>
										</td>
									</c:if>

								</tr>
							</c:forEach>
						</tbody>
					</table>

					<%@ include file="page2.file"%>

					<!--========================<!--請注意: 以上填入你要寫的東西 結束-->
			</div>
		</section>
		<!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->


	</main>
	<footer data-background="../assets/img/bg/footer.jpg">

		<div class="footer-area pt-5">
			<div class="container">
				<div class="row">
					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<div class="footer-logo mb-25">
								<img src="../assets/img/logo/Mumu_logo.png" alt="">
							</div>
							<div class="social-icon">
								<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i
									class="fab fa-twitter"></i></a> <a href="#"><i
									class="fab fa-behance"></i></a> <a href="#"><i
									class="fab fa-linkedin-in"></i></a> <a href="#"><i
									class="fab fa-youtube"></i></a>
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
	<script src="../assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/owl.carousel.min.js"></script>
	<script src="../assets/js/isotope.pkgd.min.js"></script>
	<script src="../assets/js/instafeed.min.js"></script>
	<script src="../assets/js/slick.min.js"></script>
	<script src="../assets/js/jquery.meanmenu.min.js"></script>
	<script src="../assets/js/ajax-form.js"></script>
	<script src="../assets/js/wow.min.js"></script>
	<script src="../assets/js/jquery.scrollUp.min.js"></script>
	<script src="../assets/js/imagesloaded.pkgd.min.js"></script>
	<script src="../assets/js/jquery.magnific-popup.min.js"></script>
	<script src="../assets/js/plugins.js"></script>
	<script src="../assets/js/main.js"></script>

	<!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
	<script src="../assets/js/dropdown.js"></script>
</body>
</html>