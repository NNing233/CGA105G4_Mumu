<%@page import="java.time.LocalDate"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.prop2.model.*"%>
<%@page import="com.sponrecord2.model.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.lang.StringBuilder"%>


<jsp:useBean id="propService" class="com.prop2.model.PropService"
	scope="page" />
<jsp:useBean id="sponRecordService"
	class="com.sponrecord2.model.SponRecordService" scope="page" />

<%
PropVO propVO = null;
try {
	propVO = propService.getOneProp(Integer.parseInt(request.getParameter("prop")));
	pageContext.setAttribute("propVO", propVO);
} catch (Exception e) {

	response.sendRedirect("index.jsp");
}

Timestamp ts = new Timestamp(System.currentTimeMillis());
LocalDate today = ts.toInstant().atZone(ZoneId.of("Asia/Taipei")).toLocalDate();
LocalDate endDate = propVO.getProposalEndDatetime().toInstant().atZone(ZoneId.of("Asia/Taipei")).toLocalDate();
long diffInDays = ChronoUnit.DAYS.between(today, endDate);

pageContext.setAttribute("diffInDays", diffInDays);

String location = (request.getQueryString() == null) ? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);
%>

<!doctype html>
<html class="no-js" lang="en">

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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">




<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet" href="../assets/scss/main2.css">
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

		<section class="grey-bg pt-30 pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->

				<h1 class="my-4 text-left">${ propVO.proposalName }</h1>
				<section class="grey-bg pt-10 pb-10">
			</div>
			<div class="container">
				<div class="row">
					<div class="col-8">
						<img
							src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${propVO.proposalId}"
							alt="" class="popularImg img-fuid"
							onerror="this.src='../assets/img/image_not_available.png'" />
						<div>
							<p class="mt-4"></p>
						</div>

						<div class="progress" style="height: 32px;">
							<div class="progress-bar progress-bar-striped bg-success"
								role="progressbar"
								style="width: ${100 * propVO.accumulativeDonateMoney/propVO.targetDonateMoney}%"
								aria-valuenow="67" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<div class="d-flex justify-content-between mb-4">
							<span>0 NTD</span> <span>${ propVO.targetDonateMoney } NTD</span>
						</div>
						<p class="mt-4">[專案內容]</p>
						<p>${propVO.proposalArticle}</p>
					</div>
					<div class="col-1"></div>
					<div class="col-3">
						<div class="d-flex justify-content-between mb-4">

							<div class="numberRow totalFund">
								<p class="metatext moneyFormat">目標 NTD$ ${ propVO.targetDonateMoney }</p>
								<h2 class="number moneyFormat">$ ${ propVO.accumulativeDonateMoney }</h2>

							</div>


						</div>
						<div class="d-flex justify-content-between mb-4">
							<div class="numberRow totalPeople">
								<p class="metatext">贊助人數</p>
								<h2>${ sponRecordService.getSponsorNumByProposalId(propVO.proposalId) }人</h2>
							</div>
						</div>
						<div class="d-flex justify-content-between mb-4">
							<div class="numberRow totalDays">
								<p class="metatext">募資倒數</p>
								<h2>${ diffInDays }天</h2>
							</div>
						</div>

						<div class="d-grid">
							<a type="button" href="<%=request.getContextPath()%>/propopt/propopt.do?action=listPropOpt&proposalId=${propVO.proposalId}" class="btn btn-primary ${(propVO.proposalStatus>=3)? 'd-none':'' }" style="color: white;">我要贊助</a>
							<button type="button" class="btn btn-danger ${(propVO.proposalStatus>=3)? '':'d-none' }" disabled>本專案已停止募資</button>
						</div>
					</div>
				</div>
			</div>
			</div>

			<!--請注意: 以上填入你要寫的東西 結束-->
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
	<script src="../assets/js/dropdown.js"></script>
</body>

</html>