<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopprod.model.*"%>
<jsp:useBean id="myFavoriteSvc" class="com.myfavorite.model.MyFavoriteService" scope="page" />

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
<%
ShopProdService shopProdSvc = new ShopProdService();
List<ShopProdVO> list = shopProdSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>mumu product page</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="manifest" href="site.webmanifest">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/front_end/assets/img/favicon.png">
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


<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/assets/scss/main.css">
<!--請注意: 可寫css 在main.css內 -->


<style type="text/css">
.inactive-btn {
	background: lightgray;
	padding: 20px 25px;
	border: none;
	margin-left: 15px;
	color: darkgray;
	display: inline-block;
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

		<!-- page-title-area start -->
		<section class="grey-bg pt-120 pb-120">
			<div class="container">

				<div class="row">
					<div class="col-xl-7">
						<div class="shop-thumb-tab mb-30">
							<ul class="nav" id="myTab2" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="home-tab" data-toggle="tab" href="#home" role="tab"
									aria-selected="true"><img
										src="data:image/jpg;base64,${shopProdVO.prodOptVO.get(0).productPictureString}"
										alt=""> </a></li>
								<li class="nav-item"><a class="nav-link" id="profile-tab"
									data-toggle="tab" href="#profile" role="tab"
									aria-selected="false"><img
										src="data:image/jpg;base64,${shopProdVO.prodOptVO.get(1).productPictureString}"
										alt=""></a></li>
								<li class="nav-item">
									<!--                                         <a class="nav-link" id="profile-tab2" data-toggle="tab" href="#profile1" role="tab" -->
									<%--                                             aria-selected="false"><img src="data:image/jpg;base64,${shopProdVO.prodOptVO.get(2).productPictureString}" alt=""></a> --%>
								</li>
							</ul>
						</div>
						<div class="product-details-img mb-30">
							<div class="tab-content" id="myTabContent2">
								<div class="tab-pane fade show active" id="home" role="tabpanel">
									<div class="product-large-img">
										<img
											src="data:image/jpg;base64,${shopProdVO.productPictureString}"
											alt="">
									</div>
								</div>
								<div class="tab-pane fade" id="profile" role="tabpanel">
									<div class="product-large-img">
										<img src="<%=request.getContextPath()%>/front_end/assets/img/shop/details/large2.jpg" alt="">
									</div>
								</div>
								<div class="tab-pane fade" id="profile1" role="tabpanel">
									<div class="product-large-img">
										<img src="<%=request.getContextPath()%>/front_end/assets/img/shop/details/large3.jpg" alt="">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-5">
						<div class="product-details mb-30">
							<div class="product-details-title">
								<p id="shopProdOptName">${shopProdVO.prodOptVO.get(0).productOptionName}</p>
								<h1>${shopProdVO.productName}</h1>
								<input type="hidden" name="productId" value="${shopProdVO.productId }">	

								<div class="price details-price pb-30 mb-20">
									<span>$</span><span id="shopProdPrice">${shopProdVO.prodOptVO.get(0).productOptionPrice}</span>
									<br> <br>
									<jsp:useBean id="prodOptSvc" scope="page"
										class="com.prodopt.model.ProdOptService" />
									<ul>
										<li><b>商品選項:</b> <select size="1" id="shopProdOpts"
											name="productOptionId">
												<c:forEach var="shopProdVO" items="${shopProdVO.prodOptVO}">
													<option value="${shopProdVO.productOptionId}"
														data-prodoptprice="${shopProdVO.productOptionPrice}"
														data-prodoptid="${shopProdVO.productOptionId}">
														${shopProdVO.productOptionName}</option>
												</c:forEach>
										</select></li>
									</ul>
								</div>
							</div>
							<p>${shopProdVO.productDescription}</p>
							<div class="product-cat mt-30 mb-30">
								<span>Category: </span> <a href="#">furniture,</a> <a href="#">decor</a>
							</div>
							<div class="product-social mb-45">
								<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i
									class="fab fa-twitter"></i></a> <a href="#"><i
									class="fab fa-behance"></i></a> <a href="#"><i
									class="fab fa-linkedin-in"></i></a> <a href="#"><i
									class="fab fa-youtube"></i></a>
							</div>
							<div class="product-details-action">
								<form METHOD="post" action="<%=request.getContextPath()%>/front_end/shop/shopCartList.do">
									<div class="plus-minus">
										<div class="cart-plus-minus">
											<input type="text" value="1" />
										</div>
									</div>
									<input class="btn btn-black" type="submit" value="加入購物車">
									<input type="hidden" name="action"
										value="addOneProdToShopCartList"> <input type="hidden"
										name="productOptionId" id="shopCartProdOptId" value="">
									<input type="hidden" name="memberId" id="shopCartMemId"
										value="${sessionScope.mem.memberId}"> <input
										type="hidden" name="productOptionQuantity"
										id="shopCartProdOptQty" value="">


									<c:if test="${empty mem }">
										<button class="inactive-btn" id="heartBtn">
											<i class="fas fa-heart"></i>
										</button>
									</c:if>
									<c:if test="${not empty mem }">
										<button class="${(myFavoriteSvc.getOneMyFavorite(mem.memberId, shopProdVO.productId)!=null)?'action-btn': 'inactive-btn' }" id="heartBtn">
											<i class="fas fa-heart"></i>
										</button>
									</c:if>
								</form>


							</div>
						</div>
					</div>
				</div>


			</div>
		</section>
		<!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->
		<!-- page-title-area end -->

		<!-- product-desc-area start -->
		<section class="product-desc-area pb-120">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="bakix-details-tab">
							<ul class="nav text-center justify-content-center pb-30 mb-50"
								id="myTab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="desc-tab" data-toggle="tab" href="#id-desc" role="tab"
									aria-controls="home" aria-selected="true">商品描述</a></li>

							</ul>
						</div>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="id-desc"
								role="tabpanel" aria-labelledby="desc-tab">
								<div class="row">
									<!--                                         <div class="col-md-12"> -->
									<!--                                             <div class="bakix-video mb-30"> -->
									<!--                                                 <img src="<%=request.getContextPath()%>/front_end/assets/img/shop/details/lg.jpg" alt=""> -->
									<!--                                                 <a class="popup-video" href="https://www.youtube.com/watch?v=Y6MlVop80y0"><i class="fas fa-play"></i></a> -->
									<!--                                             </div> -->
									<!--                                         </div> -->
									<div class="col-md-6">
										<div class="event-img mb-40">
											<img src="<%=request.getContextPath()%>/front_end/assets/img/shop/details/lg1.jpg" alt="">
										</div>
									</div>
									<div class="col-md-6 mb-40">
										<div class="event-img">
											<img src="<%=request.getContextPath()%>/front_end/assets/img/shop/details/lg2.jpg" alt="">
										</div>
									</div>
									<div class="col-md-12">
										<div class="event-text mb-40">
											<p>顯示 shopProdVO.productDescription</p>
											<p>${shopProdVO.productDescription}</p>
											<p>Excepteur sint occaecat cupidatat non proident, sunt
												in culpa qui officia deserunt mollit anim id est laborum.
												Sed ut perspiciatis unde omnis iste natus error sit
												voluptatem accusantium doloremque laudantium, totam rem
												aperiam, eaque ipsa quae ab illo inventore veritatis et
												quasi architecto beatae vitae dicta sunt explicabo. Nemo
												enim ipsam voluptatem quia voluptas sit aspernatur aut odit
												aut fugit, sed quia consequuntur magni dolores eos qui
												ratione voluptatem sequi nesciunt. Neque porro quisquam est,
												qui dolorem ipsum quia dolor sit amet, consectetur, adipisci
												velit, sed quia non numquam eius modi tempora.</p>
										</div>
									</div>
									<div class="col-md-12">
										<div class="event-gallery">
											<img src="<%=request.getContextPath()%>/front_end/assets/img/shop/details/shop-bottom.jpg" alt="">
										</div>
									</div>
								</div>
							</div>
							<div class="additional-info">
								<div class="event-text mb-40">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua. Ut enim ad minim veniam, quis nostrud
										exercitation ullamco laboris nisi ut aliquip ex ea commodo
										consequat. Duis aute irure dolor in reprehenderit in voluptate
										velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
										sint occaecat cupidatat non proident, sunt in culpa qui
										officia deserunt mollit anim id est laborum. Sed ut
										perspiciatis unde omnis iste natus error sit voluptatem
										accusantium doloremque laudantium, totam rem aperiam, eaque
										ipsa quae ab illo inventore veritatis et quasi architecto
										beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem
										quia voluptas sit aspernatur aut odit aut fugit, sed quia
										consequuntur magni dolores eos qui ratione voluptatem sequi
										nesciunt. Neque porro quisquam est, qui dolorem ipsum quia
										dolor sit amet, consectetur, adipisci velit, sed quia non
										numquam eius modi tempora incidunt ut labore et dolore magnam
										aliquam quaerat voluptatem.</p>
									<p>Excepteur sint occaecat cupidatat non proident, sunt in
										culpa qui officia deserunt mollit anim id est laborum. Sed ut
										perspiciatis unde omnis iste natus error sit voluptatem
										accusantium doloremque laudantium, totam rem aperiam, eaque
										ipsa quae ab illo inventore veritatis et quasi architecto
										beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem
										quia voluptas sit aspernatur aut odit aut fugit, sed quia
										consequuntur magni dolores eos qui ratione voluptatem sequi
										nesciunt. Neque porro quisquam est, qui dolorem ipsum quia
										dolor sit amet, consectetur, adipisci velit, sed quia non
										numquam eius modi tempora.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</section>
		<!-- product-desc-area end -->


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
	<script>
		$(document)
				.ready(
						function() {
							$('#shopCartProdOptQty').val(1);
							$(".qtybutton").on("click", function() {
								let qty = $('.cart-plus-minus input').val();
								$('#shopCartProdOptQty').val(qty);
							});

							$('#shopCartProdOptId').val(
									$('#shopProdOpts option:selected').data(
											'prodoptid'));
							$('#shopProdOpts')
									.change(
											function() {
												let selectedOption = $('#shopProdOpts option:selected');
												let price = selectedOption
														.data('prodoptprice');
												let id = selectedOption
														.data('prodoptid');
												$('#shopProdPrice').text(price);
												$('#shopProdOptName').text(
														selectedOption.text());
												$('#shopCartProdOptId').val(id);
											});
							
							
							$('#heartBtn').on('click', function(e) {
								
								e.preventDefault();
								
								<% if(request.getSession()==null || request.getSession().getAttribute("mem") == null){%>
									
								window.location.href = "<%=request.getContextPath()%>/front_end/login.jsp";
								
								<%}else{%>
								
								$(this).toggleClass('action-btn');
								$(this).toggleClass('inactive-btn');
								
								console.log($('input[name=productId]').val());
								let productId = $('input[name=productId]').val();
								
								$.ajax({
									url: '<%=request.getContextPath()%>/AddFavoriteServlet',
									type: 'POST',
									data: 'action=addToFavorite&productId=' + productId,
									
								}).then(function(res){
									console.log('收藏成功');
								}, function(err){
									console.log(err);
								});
								
								<%}%>
								
								
							})
							

							
						});
	</script>
</body>

</html>