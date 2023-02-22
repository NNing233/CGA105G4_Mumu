<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String location = null;
if (request.getAttribute("javax.servlet.forward.request_uri") == null) {
	location = (request.getQueryString() == null)
	? request.getRequestURL().toString()
	: request.getRequestURL().append("?").append(request.getQueryString()).toString();
} else {
	location = (request.getQueryString() == null)
	? request.getAttribute("javax.servlet.forward.request_uri").toString()
	: (request.getAttribute("javax.servlet.forward.request_uri").toString() + "?" + request.getQueryString());
}

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
	href="<%=request.getContextPath()%>/front_end/assets/img/favicon.png">
<!-- Place favicon.png in the root directory -->

<!-- CSS here -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/animate.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/magnific-popup.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/fontawesome-all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/meanmenu.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/flaticon.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/slick.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/css/responsive.css">



<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/assets/scss/main2.css">
<!--請注意: 可寫css 在main.css內 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css"
	integrity="sha512-OTcub78R3msOCtY3Tc6FzeDJ8N9qvQn1Ph49ou13xgA9VsH9+LRxoFU6EqLhW4+PKRfU+/HReXmSZXHEkpYoOA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
.owl-carousel .item {
	padding: 10px 30px;
	background-color: orange;
}

.owl-nav {
	position: absolute;
	top: -70px;
	right: 0px;
	display: block !important;
	border: 0px solid black;
}

.owl-nav i {
	font-size: 32px;
	color: #F9FAFF !important;
}

.owl-nav .owl-prev {
	margin-right: 11px !important;
}

.owl-next, .owl-prev {
	background: #737373 !important;
	border-radius: 0 !important;
}

/* .owl-next:hover:not(.disabled), .owl-prev:hover:not(.disabled) { */
/* 	background: #F9FAFF!important; */
/* } */

/* .owl-next:hover:not(.disabled) i, .owl-prev:hover:not(.disabled) i { */
/* 	color: #737373 !important; */
/* } */
.proposalType {
	background-color: #f1f1f1;
	color: #212121;
	padding: 5px 14px;
	font-weight: 600;
	font-size: 12px;
	letter-spacing: 1.2px;
	text-align: center;
	margin-bottom: 8px;
	display: inline-block;
}

.proposalOption .btn.btn-red-ghost {
	color: #f8414a;
	background-color: transparent;
	border-color: #f8414a;
}

.proposalOption .btn {
	height: 38px;
	padding: 9px 12px;
	font-size: 14px;
	font-weight: 700;
	letter-spacing: 3px;
	text-align: center;
	border: 1px solid transparent;
}

.proposalOption {
	cursor: pointer;
}

.proposalOption:hover .btn {
	background-color: #f8414a;
	color: white;
}
</style>
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

		<section class="grey-bg pt-60 pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->
				<div class="proposal d-flex align-items-center mb-5">
					<div class="proposalThumb d-none d-sm-block mr-3"
						style="flex-shrink: 0;">
						<img
							src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${propVO.proposalId}"
							alt="" style="width: 110px"
							onerror="this.src='<%=request.getContextPath()%>/front_end/assets/img/image_not_available.png'" />
					</div>
					<div>
						<span class="proposalType">${propVO.propTypeVO.proposalTypeName }</span>
						<a
							href="<%=request.getContextPath()%>/front_end/prop/prop.jsp?prop=${propVO.proposalId}"><h3
								class="proposalTitle mb-0">${propVO.proposalName }</h3></a>
					</div>
				</div>

				<h6
					style="color: #313131; font-size: 16px; letter-spacing: 1.5px; font-weight: 700;">
					<i class="fa-solid fa-circle-info"></i> 選擇您想要贊助的金額與回饋
				</h6>
				<div class="owl-carousel owl-theme">

					<c:forEach var="propOpt" items="${ propOptList }">
						<div class="proposalOption px-3 py-4"
							style="border: solid 1px lightgray;">
							<h4 style="letter-spacing: 1px; color: #F8414A;">$${propOpt.proposalOptionPrice
								}</h4>
							<div class="proposalOptionImage mb-3">
								<img
									src="<%=request.getContextPath()%>/propopt/propopt.do?action=showImg&proposalOptionId=${propOpt.proposalOptionId}"
									onerror="this.src='<%=request.getContextPath()%>/front_end/assets/img/image_not_available.png'"
									alt="" />
							</div>
							<p class="proposalOptionName"
								style="font-weight: 300; color: #333; font-size: 16px;">${propOpt.proposalOptionName }</p>
							<form method="POST"
								action="<%=request.getContextPath()%>/propopt/propopt.do"
								accept-charset="UTF-8" id="form_84163" class="reward-submit">
								<input name="proposalOptionId" type="hidden"
									value="${propOpt.proposalOptionId }"> <input
									id="reward-84163" name="money" type="hidden" value="228">
								<input name="proposalId" type="hidden"
									value="${propOpt.proposalId }"> 
								<input name="action"
									type="hidden" value="checkout">
								<button class="btn btn-red-ghost btn-block btn-submit"
									type="submit">贊助此回饋方案</button>
							</form>
						</div>
					</c:forEach>


				</div>


				<!--請注意: 以上填入你要寫的東西 結束-->
			</div>
		</section>
		<!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->


	</main>
	<footer
		data-background="<%=request.getContextPath()%>/front_end/assets/img/bg/footer.jpg">

		<div class="footer-area pt-5">
			<div class="container">
				<div class="row">
					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<div class="footer-logo mb-25">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/logo/Mumu_logo.png"
									alt="">
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
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/isotope.pkgd.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/instafeed.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/slick.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/jquery.meanmenu.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/ajax-form.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/wow.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/jquery.scrollUp.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/imagesloaded.pkgd.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/plugins.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/assets/js/main.js"></script>

	<!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
	<script
		src="<%=request.getContextPath()%>/front_end/assets/js/dropdown.js"></script>

	<script>
		$('.owl-carousel').owlCarousel(
				{
					loop : false,
					margin : 40,
					dots : false,
					nav : true,
					navText : [ "<i class='fa-solid fa-angle-left'></i>",
							"<i class='fa-solid fa-angle-right'></i>" ],
					responsive : {
						0 : {
							items : 1
						},
						768 : {
							items : 2
						},
						992 : {
							items : 3
						}
					}
				});

		$('.owl-carousel').on('click', '.proposalOption', function() {
			$(this).find('form').submit();
		})
	</script>
</body>

</html>