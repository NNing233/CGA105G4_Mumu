<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.art.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
HttpSession Asession = request.getSession();
String location = (request.getQueryString() == null)
		? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();

Asession.setAttribute("location", location);
%>

<c:if test="${empty sessionScope.mem}">
	<jsp:forward page="../login.jsp" />
</c:if>

<%
int currentUserId = ((MemVO) request.getSession().getAttribute("mem")).getMemberId();
List<ArtVO> list = new ArtService().getAll().stream().filter(artVO -> artVO.getMemberId() == currentUserId)
		.collect(Collectors.toList());
pageContext.setAttribute("list", list);
%>


<%
ArtVO artVO = (ArtVO) request.getAttribute("artVO");
%>


<!DOCTYPE html>
<html class="no-js" lang="en">
<html>

<head>







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
<!--?????????: ????????? fontawesome -->
<link rel="stylesheet" href="../assets/scss/main.css">
<!--?????????: ??????css ???main.css??? -->
</head>
<body>


	<!-- preloader -->
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- preloader end  -->

	<header id="sticky-header " class="header">
		<div class="container">
			<div class="header-box white-bg pl-50 pr-50">
				<div class="row">
					<div
						class="col-xl-2 col-lg-2 col-md-3 col-5 d-flex align-items-center">
						<div class="header__logo">
							<a href="<%=request.getContextPath()%>/front_end/prop/index.jsp"><img
								src="<%=request.getContextPath()%>/front_end/assets/img/logo/Mumu_logo.png" alt=""></a>
						</div>
					</div>
					<div class="col-xl-10 col-lg-10 col-7 col-md-9">
						<div class="header__right f-right" style="overflow: visible;">
							<div class="header__icon f-right mt-30 position-relative">
								<a class="login-btn" onclick="dropdownMenu(event)"><img style="width: 100%;height: 100%"  src="<%=request.getContextPath()%>/front_end/mem/photo?action=show_image&memberId=${sessionScope.mem.memberId}"></a>


								<!--?????????: ??????????????????????????? -->

								<div class="dropdown position-absolute">
									<button class="btn btn-secondary dropdown-toggle" type="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"></button>

									<div class="dropdown-menu dropdown-menu-right"
										aria-labelledby="dropdownMenuButton" style="z-index: 100">
										<c:if test="${empty sessionScope.mem}">
											<a class="dropdown-item">????????? ?????????</a>
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/front_end/login.jsp">??????</a>
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/front_end/register.jsp">??????</a>
										</c:if>
										<c:if test="${not empty sessionScope.mem}">
											<a class="dropdown-item">Hi???
												${sessionScope.mem.memberNickname}???</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/mem/personalpage.jsp">????????????</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/sponrecord/sponrecord.do?action=getSponsorRecordByMember">????????????</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/prop/funding_proposal_management.jsp">????????????</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/propcollection/propcollection.do?action=MyPropCollection">????????????</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/art/artManage.jsp">????????????</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/shop/shop-cart.jsp">???????????????</a>
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/front_end/log.do?action=logout">??????</a>
										</c:if>
									</div>

								</div>

								<!--?????????: ??????????????????????????? ?????? -->


							</div>
							<div class="header__icon f-right mt-30 ml-30 d-none d-md-block">
								<a class="btn btn-white " href="<%=request.getContextPath() %>/front_end/prop/addProposal.jsp"><i
									class="fa-solid fa-sack-dollar"></i>????????????</a>
							</div>



						</div>
						<div class="header__menu f-right">
							<nav id="mobile-menu">
								<ul>
									<li><a href="<%=request.getContextPath()%>/front_end/prop/index.jsp">??????</a>
										<ul class="submenu">
											<li><a href="<%=request.getContextPath()%>/front_end/prop/addPropReport.jsp">????????????</a></li>
										</ul></li>

									<li><a href="<%=request.getContextPath()%>/front_end/shop/shop-main.jsp">??????</a>
										<ul class="submenu">
											<li><a
												href="<%=request.getContextPath()%>/front_end/order/listOneMemberOrderHistoryTest2.jsp">????????????</a></li>
											<li><a
												href="<%=request.getContextPath()%>/front_end/shop/listOneMemberFavorite.jsp">????????????</a></li>
											<li><a
												href="<%=request.getContextPath()%>/front_end/coupon/listOneMemberCouponTest1.jsp">???????????????</a></li>
										</ul></li>

									<li><a href="<%=request.getContextPath()%>/front_end/art/art.jsp">?????????</a></li>

									<li><a
										href="<%=request.getContextPath()%>/front_end/art/addArt.jsp">????????????</a>
									</li>

									<li><a href="contact.html">????????????</a></li>


								</ul>
							</nav>
						</div>
					</div>
					<div class="col-12">
						<div class="mobile-menu"></div>
					</div>
				</div>
			</div>
		</div>
		<!--?????????????????????????????????head???-->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<script
			src="https://cdn.tiny.cloud/1/no-api-key/tinymce/6/tinymce.min.js"
			referrerpolicy="origin"></script>

		<script>
			tinymce
					.init({
						selector : '#mytextarea',
						plugins : [ 'a11ychecker', 'advlist', 'advcode',
								'advtable', 'autolink', 'checklist', 'export',
								'lists', 'link', 'image', 'charmap', 'preview',
								'anchor', 'searchreplace', 'visualblocks',
								'powerpaste', 'fullscreen', 'formatpainter',
								'insertdatetime', 'media', 'table', 'help',
								'wordcount' ],
						toolbar : 'undo redo | formatpainter casechange blocks | bold italic backcolor | '
								+ 'alignleft aligncenter alignright alignjustify | '
								+ 'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help'
					});
		</script>

	</header>
	<!-- ?????????:--?????????????????????????????????head???-->
	<main>
		<!--?????????: ?????????????????????????????? ??????-->
		<section class="gre
		y-bg pt-120 pb-120">
			<div class="container">


				<h3>????????????</h3>

				<form METHOD="post" ACTION="art.do" name="form1"
					enctype="multipart/form-data">

					<div class="form-group">
						<label for="exampleFormControlInput1" style="color: black">????????????</label>
						<input type="TEXT" class="form-control"placeholder="?????????????????????"
							id="exampleFormControlInput1" name="articleTitle"
							value="<%=(artVO == null) ? "" : artVO.getArticleTitle()%>">
						
					</div>

					<div class="form-group">
						<label for="exampleFormControlSelect1" style="color: black">?????????????????????</label>
						<select class="form-control" id="exampleFormControlSelect1"
							name="articleTypeId">
							<option selected>?????????</option>
							<jsp:useBean id="artTypeSvc" scope="page"
								class="com.arttype.model.ArtTypeService" />
							<c:forEach var="artTypeVO" items="${artTypeSvc.all}">
								<option value="${artTypeVO.articleTypeId}"
									${(artVO.articleId==artTypeVO.articleTypeId)? 'selected':'' }>${artTypeVO.articleTypeName}
								</option>
							</c:forEach>
						</select>
					</div>

					<label for="exampleFormControlTextarea1" style="color: black">????????????</label>

					<textarea id="mytextarea" name="articleContent"
						class="form-control" id="exampleFormControlTextarea1" rows="6"
						value="<%=(artVO == null) ? "?????????????????????" : artVO.getArticleContent()%>"></textarea>

					<input type="hidden" name="memberId"
						value="${sessionScope.mem.memberId}"> <input type="hidden"
						name="action" value="insert"> <input
						class="btn btn-primary" type="submit" name="action" value="????????????">
				</form>




				<!--?????????: ?????????????????????????????? ??????-->
			</div>
		</section>
		<!--?????????: ???????????????????????????????????????????????? section????????????main????????? -->


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
							<h4 class="footer-title">?????? Mumu</h4>
							<ul class="footer-link">
								<li><a href="#">????????????</a></li>

							</ul>
						</div>
					</div>
					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<h4 class="footer-title">????????????</h4>
							<ul class="footer-link">
								<li><a href="#">??????????????????</a></li>


							</ul>
						</div>
					</div>

					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<h4 class="footer-title">????????????</h4>
							<ul class="footer-link">
								<li><a href="#">?????????</a></li>
								<li><a href="#">- ????????????????????????</a></li>
								<li><a href="#">- ???????????????????????????</a></li>
								<li><a href="#">- ??????????????????</a></li>
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

	<!-- ?????????: ??????????????? js????????? dropdown?????? -->
	<script src="../assets/js/dropdown.js"></script>

</body>
</html>