<%@page import="com.prop2.model.PropService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.proptype.model.PropTypeService"%>
<%@page import="com.proptype.model.*"%>
<%@page import="com.prop2.model.*"%>
<%@ page import="java.util.*"%>



<%
PropTypeService propTypeSvc = new PropTypeService();
List<PropTypeVO> propTypeList = propTypeSvc.getActivePropType();
pageContext.setAttribute("propTypeList", propTypeList);

PropService propSvc = new PropService();
List<PropVO> propList = propSvc.getAll();
pageContext.setAttribute("propList", propList);
pageContext.setAttribute("popularProp", propSvc.getPopularProp());

String location = (request.getQueryString() == null)
		? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);
%>

<jsp:useBean id="propCollectionSvc"
	class="com.propcollection.model.PropCollectionService" scope="page" />


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
<link rel="stylesheet" href="../assets/css/flaticon.css">
<link rel="stylesheet" href="../assets/css/meanmenu.css">
<link rel="stylesheet" href="../assets/css/slick.css">
<link rel="stylesheet" href="../assets/css/style.css">
<link rel="stylesheet" href="../assets/css/responsive.css">

<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet" href="../assets/scss/main2.css">
<style>
.popularImgDiv {
	overflow: hidden;
}

.popularImg:hover {
	transform: scale(1.03);
	transition: all 0.2s ease-in;
}

.liked {
	visibility: visible !important;
	opacity: 1 !important;
	background: #1b70f0 !important;
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

	<!-- header start -->
	<%@ include file="/front_end/header/header.jsp"%>
	<!-- header end -->

	<main>
		<!-- page-title-area start -->
		<section id="populorProp" class="page-title-area pt-60 pb-60">
			<a href="prop.jsp?prop=${popularProp.proposalId}">
				<div class="container">
					<div class="popularImgDiv w-100 rounded-top">
						<img
							src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${popularProp.proposalId}"
							alt="" class="popularImg img-fuid"
							onerror="this.src='../assets/img/image_not_available.png'" />
					</div>
					<div class="row" style="text-align: center;">
						<div class="col-sm-9 pr-sm-0">
							<div
								class="page-title py-4 h-100 d-flex justify-content-center align-items-center px-4"
								style="border-bottom-left-radius: 0.25rem; background-color: #efefef;">
								<h4 style="font-weight: 300;">${popularProp.proposalName}</h4>
							</div>
						</div>
						<div class="col-sm-3 pl-sm-0">
							<div
								class="page-title h-100 py-5 d-flex justify-content-center align-items-center"
								style="background-color: #059669; border-bottom-right-radius: 0.25rem;">
								<div class="accumulatePrice">
									<h4 style="color: white; text-align: right; margin: 0">
										累積金額 <br class="d-none d-sm-block">NT$
										${popularProp.accumulativeDonateMoney}
									</h4>
								</div>

							</div>
						</div>
					</div>
				</div>
			</a>
		</section>
		<!-- page-title-area end -->



		<!-- causes-area start -->
		<section class="causes-area grey-bg pt-80 pb-80">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-xl-9 col-lg-9" id="propListSection">
						<div class="section-title mb-65">
							<p style="font-weight: 900;">
								<span></span> 專案類別
							</p>
							<h1>在這裡，你可以探索不同的專案</h1>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 d-none d-xl-block">
						<div class="section-link mb-65 text-right">
							<a class="btn-border" href="#">other projects</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<div class="causes-tab">

							<ul class="nav theme-bg text-center" id="myTab" role="tablist">
								<c:forEach var="propTypeVO" items="${ propTypeList }">
									<li><a
										href="<%=request.getContextPath()%>/front_end/propType/propType.jsp?propType=${propTypeVO.proposalTypeId}"
										style="font-weight: 900; font-size: 16px; padding: 2px 10px;">${ propTypeVO.proposalTypeName }</a>
									</li>
								</c:forEach>

							</ul>
							<h3 class="mt-5 mb-3" style="font-weight: 500;">群眾集資</h3>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="home" role="tabpanel"
									aria-labelledby="home-tab">
									<div class="row">
										<%@ include file="page1.file"%>

										<c:forEach var="propVO" items="${ propList }"
											begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

											<div class="col-xl-4 col-lg-4 col-md-6">
												<div class="causes white-bg mb-30">
													<div class="causes__img">
														<img
															src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${propVO.proposalId}"
															alt=""
															onerror="this.src='../assets/img/image_not_available.png'">
														<div class="causes-heart">
															<c:if test="${not empty mem}">
																<a href="#"
																	class="${(propCollectionSvc.getOnePropCollection(mem.memberId,propVO.proposalId) != null)? 'liked':'' }">
																	<i class="far fa-heart"></i>
																	<form style="display: none;">
																		<input type="hidden" name="proposalId"
																			value="${propVO.proposalId}" /><input type="hidden"
																			name="memberId" value="${mem.memberId}" /><input
																			type="hidden" name="action" value="likeProposal" />
																	</form>
																</a>
															</c:if>
															<c:if test="${empty mem}">
																<a href="#"> <i class="far fa-heart"></i>
																	<form style="display: none;">
																		<input type="hidden" name="proposalId"
																			value="${propVO.proposalId}" /><input type="hidden"
																			name="memberId" value="${mem.memberId}" /><input
																			type="hidden" name="action" value="likeProposal" />
																	</form>
																</a>
															</c:if>

														</div>
													</div>
													<div class="causes__caption">
														<div class="causes-tag mb-20">
															<a href="#" style="font-weight: 600;">${propVO.propTypeVO.proposalTypeName}</a>
														</div>
														<h4>
															<a href="prop.jsp?prop=${ propVO.proposalId }">${propVO.proposalName}</a>
														</h4>
														<div class="causes-progress">
															<div class="progress">
																<div class="progress-bar" role="progressbar"
																	aria-valuenow="60" aria-valuemin="0"
																	aria-valuemax="100" style="width: ${100 * propVO.accumulativeDonateMoney/propVO.targetDonateMoney}%"></div>
															</div>
															<div class="causes-count mt-15 fix">
																<div class="count-number f-left text-left">
																	<h2>$ ${propVO.accumulativeDonateMoney }</h2>
																	<span>當前金額</span>
																</div>
																<div class="count-number f-right text-right">
																	<h2>$ ${propVO.targetDonateMoney }</h2>
																	<span>目標金額</span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>


										</c:forEach>



									</div>

								</div>




							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<div class="basic-pagination text-center mt-30">
							<ul>
								<%@ include file="page2.file"%>
								<!-- 								<li><a href="#"><i class="fas fa-ellipsis-h"></i></a></li> -->
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- causes-area end -->





	</main>

	<!-- footer start -->
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
	<!-- footer end -->




	<!-- JS here -->
	<script src="../assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/owl.carousel.min.js"></script>
	<script src="../assets/js/isotope.pkgd.min.js"></script>
	<script src="../assets/js/one-page-nav-min.js"></script>
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

	<script>
		$('.causes__img .causes-heart a').on('click', function(e){
			e.preventDefault();
			$(this).toggleClass('liked');
			let formValues = $(this).children("form").serialize();
			
			//先判別登入
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/mem/checkLogin.do'
			}).then(function(res){
				//回傳有登入，再做收藏
				if(res == 'logged') {
					$.ajax({
			        	  type: 'POST',
			        	  url: '<%=request.getContextPath()%>/propcollection/propcollection.do',
			        	  data: formValues

			        	}).then(function(res){
			        		
			        		console.log("收藏成功");
			        		
			        	}, function(err) {
			        		if(err.status == 404) {
			        			console.log("這是404錯誤");
			        		} else {
			        			console.log(err);
			        		}
			        		
			        	});
				} else {
							window.location.href = '<%=request.getContextPath()%>/front_end/login.jsp';
												}
											})

						})
	</script>
</body>
</html>