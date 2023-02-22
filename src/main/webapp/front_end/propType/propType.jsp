<%@page import="com.prop2.model.PropService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.proptype.model.PropTypeService"%>
<%@page import="com.proptype.model.*"%>
<%@page import="com.prop2.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="propCollectionSvc"
	class="com.propcollection.model.PropCollectionService" scope="page" />



<%

PropTypeService propTypeSvc = new PropTypeService();
List<PropTypeVO> propTypeList = propTypeSvc.getActivePropType();
pageContext.setAttribute("propTypeList", propTypeList);

String propType = request.getParameter("propType");
int min = (request.getParameter("min") == null || request.getParameter("min").isEmpty())?-1: Integer.parseInt(request.getParameter("min"));
int max = (request.getParameter("max") == null || request.getParameter("max").isEmpty())?-1: Integer.parseInt(request.getParameter("max"));
int temp;

if(min!=-1 && max!=-1 && min > max){
	temp = min;
	min = max;
	max = temp;
}

int proptype = (propType==null)?0:Integer.parseInt(propType);

List<PropVO> propList = null;
PropService propSvc = new PropService();

if ((propType == null || propType.equals("0")) && min==-1 && max==-1) {
	propList = propSvc.getAll();
} else if(min==-1 && max==-1) {
	propList = propSvc.getProposalFromType(Integer.parseInt(propType));
} else {
	propList = propSvc.getCompositeQuery(proptype,min,max);
}

pageContext.setAttribute("propList", propList);
pageContext.setAttribute("popularProp", propSvc.getPopularProp());

String location = (request.getQueryString() == null)
		? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);

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

#searchPricebtn:hover {
	background-color: #0A58CA !important;
	transition: 0.2s !important;
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

		<!-- causes-area start -->
		<section class="causes-area grey-bg pt-60 pb-80">
			<div class="container">

				<div class="row">
					<div class="col-12">
						<div class="causes-tab">

							<form action="propType.jsp">
								<div class="form-group">

									<h3
										style="font-weight: 300; vertical-align: top; display: inline-block; margin-top: 7px;">專案種類</h3>
									<select class="form-control ml-3 form-control-lg" id="propType" name="propType"
										style="width: fit-content; display: inline-block; vertical-align: top; font-size: 24px; background-color: #F4F4F5; border: none; cursor: pointer;">
										<option style="font-size: 18px;" value="0">全部</option>
										<c:forEach var="propTypeVO" items="${ propTypeList }">
											<option style="font-size: 18px;"
												value="${ propTypeVO.proposalTypeId }">${ propTypeVO.proposalTypeName }</option>
										</c:forEach>
									</select>

									<h3
										style="font-weight: 300; vertical-align: top; display: inline-block; margin-top: 7px;">
										&emsp;<b>x</b>&ensp;價格區間
									</h3>
									<input class="form-control form-control-lg ml-3" type="text"
										placeholder="min" name="min" pattern="[0-9]+" title="請輸入數字" value="${param.min }"
										style="display: inline-block; width: 120px; background-color: #F4F4F5; cursor: pointer;">
									<p
										style="display: inline-block; font-weight: 300; vertical-align: top; display: inline-block; margin-top: 12px; color: #333">
										&emsp;-&emsp;</p>
									<input class="form-control form-control-lg" type="text"
										placeholder="max" name="max" pattern="[0-9]+" title="請輸入數字" value="${param.max }"
										style="display: inline-block; width: 120px; background-color: #F4F4F5; cursor: pointer;">

									<button type="submit" class="btn btn-primary mb-1 ml-3" id="searchPricebtn"
										style="color: #fff; background-color: #007bff; border-color: #007bff; font-weight: 400; display: inline-block; padding: 0rem 1.2rem; font-size: 1rem; line-height: 1.5; border-radius: 0.25rem; font-size: 17px;height: 45px;">搜尋</button>
								</div>

							</form>

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
																<a href="#">
																	<i class="far fa-heart"></i>
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
															<a href="<%=request.getContextPath()%>/front_end/prop/prop.jsp?prop=${ propVO.proposalId }">${propVO.proposalName}</a>
														</h4>
														<div class="causes-progress">
															<div class="progress">
																<div class="progress-bar w-75" role="progressbar"
																	aria-valuenow="60" aria-valuemin="0"
																	aria-valuemax="100"></div>
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
										<c:if test="${empty propList}">
											<p
												style="margin-left: 15px; font-size: 16px; color: #444; font-weight: 600; letter-spacing: 1.5px;">目前沒有線上專案，可點選其他分類的專案看看！</p>
										</c:if>



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
		const queryString = window.location.search;
		const urlParams = new URLSearchParams(queryString);
		const propType = urlParams.get('propType');

		$('#propType').val(propType);

		if(propType == null) {
			$('#propType').val(0);
		}
		
		$('#propType').on('change', function() {
			console.log($(this).val());
			window.location.href = 'propType.jsp?propType=' + $(this).val();

		});

		// 處理收藏
		$('.causes__img .causes-heart a').on('click', function(e){
			e.preventDefault();
			$(this).toggleClass('liked');
			let formValues = $(this).children("form").serialize();
			console.log($(this));
	
				<%if(request.getSession().getAttribute("mem")!=null) { %>
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
				<% }else { %>	
					window.location.href = '<%=request.getContextPath()%>/front_end/login.jsp';
				<% } %>
				
		});
						
	</script>
</body>
</html>