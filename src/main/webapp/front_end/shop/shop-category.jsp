<%@ page import="com.shopprod.model.ShopProdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopprod.model.*"%>

<%
String prodDetail = "/front_end/shop/productDetail.do?action=getOne_For_Display&productId=";
%>

<!DOCTYPE html>
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
	href="<%=request.getContextPath()%>/front_end/assets/scss/main.css">
<!--請注意: 可寫css 在main.css內 -->
</head>

<body>


	<%@ include file="/front_end/header/header.jsp"%>

	<main>

		<section class="grey-bg pt-10 pb-100">
			<div class="container">
				<div class="container">
					<!--請注意: 以下填入你要寫的東西 開始-->
					<!--**************搜尋商品 模糊查詢**************-->

					<div class="container">
						<div class="col-4 mx-auto">
							<form
								ACTION="<%=request.getContextPath()%>/shoppagesearch/shoppagesearch.do"
								class="search-form" METHOD="post">
								<div class="widget mb-60 " style="border: 0px;">

									<div class="widget-title-box mb-10">
										<h3 class="widget-title">搜尋商品</h3>
									</div>
									<div style="display: flex; align-items: center;">
										<input type="text" name="productName" style="height: 60px;">
										<button type="submit" value="搜尋商品名"
											style="height: 60px; width: 60px; margin-top: 67px;">
											<i class="fas fa-search" aria-hidden="true"></i>
										</button>
									</div>

									<%--*************** 錯誤表列 ***************--%>
									<c:if test="${not empty errorMsgs}">
										<div class="d-inline">
											<h5 style="color: blue" class="mr-4">請修正以下錯誤:</h5>
											<ul class="ml-5 d-inline">
												<c:forEach var="message" items="${errorMsgs}">
													<li class="d-inline ml-5" style="color: blue">${message}</li>
												</c:forEach>
											</ul>
										</div>
									</c:if>

									<input type="hidden" name="action"
										value="listProducts_ByKeyword">
								</div>
							</form>
						</div>
					</div>

					<!--**************結束搜尋按鈕**************-->


					<div class="row">
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="shop-service text-center mb-30">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/icon/icon1.png"
									alt="">
								<h4>滿額免運</h4>
								<p>單筆訂單金額超過 $1000</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="shop-service text-center mb-30">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/icon/icon2.png"
									alt="">
								<h4>快速送達</h4>
								<p>下單後一周內送到</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="shop-service text-center mb-30">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/icon/award.png"
									alt="">
								<h4>品質保證</h4>
								<p>客戶滿意度高</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="shop-service text-center mb-30">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/icon/icon4.png"
									alt="">
								<h4>不定期折扣</h4>
								<p>多種特價商品</p>
							</div>
						</div>
					</div>

					<!--**************輪播圖start**************-->

					<div id="carouselExampleControls" class="carousel slide"
						data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/carousel/carousel3.png"
									class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/carousel/carousel4.png"
									class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img
									src="<%=request.getContextPath()%>/front_end/assets/img/carousel/carousel5.png"
									class="d-block w-100" alt="...">
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleControls"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleControls"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>

					<!--**************輪播圖end**************-->
					<br />

					<!--*****************開始商品分類*****************-->

					<div class="row mb-50">
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="shop-cat text-center mb-30">
								<a
									href="<%=request.getContextPath()%>/shoptype/shoptype.do?action=listProductByType&prodType=4">
									<img
									src="<%=request.getContextPath()%>/front_end/assets/img/shoppageicon/tea.png"
									width="80" height="80" border="0" alt="">
									<h4>生活</h4>
								</a>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="shop-cat text-center mb-30">
								<a
									href="<%=request.getContextPath()%>/shoptype/shoptype.do?action=listProductByType&prodType=1">
									<img
									src="<%=request.getContextPath()%>/front_end/assets/img/shoppageicon/speaker.png"
									width="80" height="80" border="0" alt="">
									<h4>科技設計</h4>
								</a>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="shop-cat text-center mb-30">
								<a
									href="<%=request.getContextPath()%>/shoptype/shoptype.do?action=listProductByType&prodType=3">
									<img
									src="<%=request.getContextPath()%>/front_end/assets/img/shoppageicon/movie-camera.png"
									width="80" height="80" border="0" alt="">
									<h4>藝術影視</h4>
								</a>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="shop-cat text-center mb-30">
								<a
									href="<%=request.getContextPath()%>/shoptype/shoptype.do?action=listProductByType&prodType=2">
									<img
									src="<%=request.getContextPath()%>/front_end/assets/img/shoppageicon/music.png"
									width="80" height="80" border="0" alt="">
									<h4>音樂</h4>
								</a>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="shop-cat text-center mb-30">
								<a
									href="<%=request.getContextPath()%>/shoptype/shoptype.do?action=listProductByType&prodType=6">
									<img
									src="<%=request.getContextPath()%>/front_end/assets/img/shoppageicon/game-console.png"
									width="80" height="80" border="0" alt="">
									<h4>遊戲出版</h4>
								</a>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="shop-cat text-center mb-30">
								<a
									href="<%=request.getContextPath()%>/shoptype/shoptype.do?action=listProductByType&prodType=5">
									<img
									src="<%=request.getContextPath()%>/front_end/assets/img/shoppageicon/teamwork.png"
									width="80" height="80" border="0" alt="">
									<h4>公共在地</h4>
								</a>
							</div>
						</div>
					</div>


					<!--**************結束商品分類**************-->

					<div class="row mt-20">
						<div class="col-xl-4 col-lg-5 col-md-6">
							<div class="product-showing mb-40">
								<!--                                 <p>Showing 1–22 of 32 results</p> -->
							</div>
						</div>
						<div class="col-xl-8 col-lg-7 col-md-6">
							<div class="shop-tab f-right">
								<ul class="nav text-center" id="myTab" role="tablist">
									<li class="nav-item"><a class="nav-link active"
										id="home-tab" data-toggle="tab" href="#home" role="tab"
										aria-controls="home" aria-selected="true"><i
											class="fas fa-list-ul"></i> </a></li>
									<!-- 								<li class="nav-item"> -->
									<!--                                         <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" -->
									<!--                                             aria-selected="false"><i class="fas fa-th-large"></i></a> -->
									<!--                                     </li> -->
								</ul>
							</div>
							<!--                             <div class="pro-filter mb-40 f-right"> -->
							<form action="#">
								<!--                                     <select name="pro-filter" id="pro-filter"> -->
								<!--                                         <option value="1">Shop By </option> -->
								<!--                                         <option value="2">Top Sales </option> -->
								<!--                                         <option value="3">New Product </option> -->
								<!--                                         <option value="4">A to Z Product </option> -->
								<!--                                     </select> -->
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel"
								aria-labelledby="home-tab">
								<div class="row">

									<c:forEach var="shopProdVO" items="${shopProdList }">

										<c:if test="${not empty shopProdVO.prodOptVO}">
											<div class="col-lg-4 col-md-6">
												<div class="product mb-40">
													<div class="product__img"
														style="display: flex; justify-content: center;">
														<a
															href="<%=request.getContextPath()%><%= prodDetail %>${shopProdVO.productId}">
															<img
															src="<%=request.getContextPath()%>/front_end/shop/getImage.do?productId=${shopProdVO.productId}"
															style="width: 150px;"">
														</a>
														<!-- 														<div class=" product-action text-center"> -->
														<!-- 															<a -->
														<%-- 																href="<%=request.getContextPath()%><%= prodDetail %>${shopProdVO.productId}"><i --%>
														<!-- 																class="fas fa-shopping-cart"></i></a> <a -->
														<%-- 																href="<%=request.getContextPath()%><%= prodDetail %>${shopProdVO.productId}"><i --%>
														<!-- 																class="fas fa-heart"></i></a> <a -->
														<%-- 																href="<%=request.getContextPath()%><%= prodDetail %>${shopProdVO.productId}"><i --%>
														<!-- 																class="fas fa-expand"></i></a> -->
														<!-- 														</div> -->
													</div>
													<div class="product__content text-center pt-35">
														<span class="pro-cat"><a href="#">分類:${shopProdVO.prodTypeVO.productTypeName}</a></span>
														<h4 class="pro-title">
															<a
																href="<%=request.getContextPath()%><%= prodDetail %>${shopProdVO.productId}">${shopProdVO.productName}</a>
														</h4>
														<div class="price">

															<span>$${shopProdVO.prodOptVO.get(0).productOptionPrice}</span>
															<span class="old-price">$120.00</span>
														</div>
													</div>
												</div>
											</div>
										</c:if>

									</c:forEach>

								</div>
							</div>
							<div class="tab-pane fade" id="profile" role="tabpanel"
								aria-labelledby="profile-tab">
								<div class="row mb-30">
									<div class="col-lg-4 col-md-6">
										<div class="product mb-20">
											<div class="product__img">
												<a href="porduct-details.html"><img
													src="<%=request.getContextPath()%>/front_end/assets/img/shop/img2.jpg"
													alt=""></a>
											</div>
										</div>
									</div>
									<div class="col-lg-8">
										<div class="product-list-content pt-10">
											<div class="product__content mb-20">
												<span class="pro-cat"><a href="#">Cloths</a></span>
												<h4 class="pro-title">
													<a href="porduct-details.html">Bakix Furniture</a>
												</h4>
												<div class="price">
													<span>$95.00</span> <span class="old-price">$120.00</span>
												</div>
											</div>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate.</p>
											<div class="product-action-list">
												<a class="btn btn-theme" href="#">add to cart</a> <a
													class="action-btn" href="#"><i class="fas fa-heart"></i></a>
												<a class="action-btn" href="porduct-details.html"><i
													class="fas fa-expand"></i></a>
											</div>
										</div>
									</div>
								</div>
								<div class="row mb-30">
									<div class="col-lg-4 col-md-6">
										<div class="product mb-20">
											<div class="product__img">
												<a href="porduct-details.html"><img
													src="<%=request.getContextPath()%>/front_end/assets/img/shop/img3.jpg"
													alt=""></a>
											</div>
										</div>
									</div>
									<div class="col-lg-8">
										<div class="product-list-content pt-10">
											<div class="product__content mb-20">
												<span class="pro-cat"><a href="#">Cloths</a></span>
												<h4 class="pro-title">
													<a href="porduct-details.html">Bakix Furniture</a>
												</h4>
												<div class="price">
													<span>$95.00</span> <span class="old-price">$120.00</span>
												</div>
											</div>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate.</p>
											<div class="product-action-list">
												<a class="btn btn-theme" href="#">add to cart</a> <a
													class="action-btn" href="#"><i class="fas fa-heart"></i></a>
												<a class="action-btn" href="porduct-details.html"><i
													class="fas fa-expand"></i></a>
											</div>
										</div>
									</div>
								</div>
								<div class="row mb-30">
									<div class="col-lg-4 col-md-6">
										<div class="product mb-20">
											<div class="product__img">
												<a href="porduct-details.html"><img
													src="<%=request.getContextPath()%>/front_end/assets/img/shop/img4.jpg"
													alt=""></a>
											</div>
										</div>
									</div>
									<div class="col-lg-8">
										<div class="product-list-content pt-10">
											<div class="product__content mb-20">
												<span class="pro-cat"><a href="#">Cloths</a></span>
												<h4 class="pro-title">
													<a href="porduct-details.html">Bakix Furniture</a>
												</h4>
												<div class="price">
													<span>$95.00</span> <span class="old-price">$120.00</span>
												</div>
											</div>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate.</p>
											<div class="product-action-list">
												<a class="btn btn-theme" href="#">add to cart</a> <a
													class="action-btn" href="#"><i class="fas fa-heart"></i></a>
												<a class="action-btn" href="porduct-details.html"><i
													class="fas fa-expand"></i></a>
											</div>
										</div>
									</div>
								</div>
								<div class="row mb-30">
									<div class="col-lg-4 col-md-6">
										<div class="product mb-20">
											<div class="product__img">
												<a href="porduct-details.html"><img
													src="<%=request.getContextPath()%>/front_end/assets/img/shop/img5.jpg"
													alt=""></a>
											</div>
										</div>
									</div>
									<div class="col-lg-8">
										<div class="product-list-content pt-10">
											<div class="product__content mb-20">
												<span class="pro-cat"><a href="#">Cloths</a></span>
												<h4 class="pro-title">
													<a href="porduct-details.html">Bakix Furniture</a>
												</h4>
												<div class="price">
													<span>$95.00</span> <span class="old-price">$120.00</span>
												</div>
											</div>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate.</p>
											<div class="product-action-list">
												<a class="btn btn-theme" href="#">add to cart</a> <a
													class="action-btn" href="#"><i class="fas fa-heart"></i></a>
												<a class="action-btn" href="porduct-details.html"><i
													class="fas fa-expand"></i></a>
											</div>
										</div>
									</div>
								</div>
								<div class="row mb-50">
									<div class="col-lg-4 col-md-6">
										<div class="product mb-20">
											<div class="product__img">
												<a href="porduct-details.html"><img
													src="<%=request.getContextPath()%>/front_end/assets/img/shop/img6.jpg"
													alt=""></a>
											</div>
										</div>
									</div>
									<div class="col-lg-8">
										<div class="product-list-content pt-10">
											<div class="product__content mb-20">
												<span class="pro-cat"><a href="#">Cloths</a></span>
												<h4 class="pro-title">
													<a href="porduct-details.html">Bakix Furniture</a>
												</h4>
												<div class="price">
													<span>$95.00</span> <span class="old-price">$120.00</span>
												</div>
											</div>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate.</p>
											<div class="product-action-list">
												<a class="btn btn-theme" href="#">add to cart</a> <a
													class="action-btn" href="#"><i class="fas fa-heart"></i></a>
												<a class="action-btn" href="porduct-details.html"><i
													class="fas fa-expand"></i></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</section>
		<!-- shop-banner-area end -->









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
</body>

</html>