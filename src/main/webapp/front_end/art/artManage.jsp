<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.art.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.favoriteart.model.*"%>
<%@ page import="com.artpic.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

Integer articleId= (Integer)request.getAttribute("articleId");
ArtService artSvc =new ArtService();
ArtPicService artPicSvc =new ArtPicService();
FavoriteArtService favoriteArtSvc =new FavoriteArtService();

// Integer memberId= ((MemVO)(request.getSession().getAttribute("mem"))).getMemberId();
MemVO mem = (MemVO) (request.getSession().getAttribute("mem"));
List<FavoriteArtVO> favoriteArtList = favoriteArtSvc.findByMemberID(mem.getMemberId());
pageContext.setAttribute("favoriteArtList", favoriteArtList);
%>



<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>funding-proposal-management.jsp</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
<!-- <link rel="stylesheet" href="assets/css/style.css"> -->
<link rel="stylesheet" href="../assets/css/responsive.css">

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet" href="../assets/scss/main.css">
<!--請注意: 可寫css 在main.css內 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.black-bold {
	color: black;
	font-weight: bold;
	margin-top: 3px;
}

.list-group-item:hover {
	background-color: #f5f5f5;
}

#carousel-example-generic a.carousel-control {
	height: 26%;
	top: 33%;
	width: 86px;
	background: black;
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
		<div class="maincontainer">
			<!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->
			<section>

				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="art.jsp">交流區首頁</a></li>
					<li class="breadcrumb-item active" aria-current="page">文章管理</li>
				</ol>
				<div style="text-align: center;">
					<h3>文章管理</h3>
				</div>
				<div class="row" content-left>
					<div class="col-2">
						<div class="nav flex-column nav-pills" id="v-pills-tab"
							role="tablist" aria-orientation="vertical">

							<button href="addArt.jsp" class="nav-link active"
								id="v-pills-home-tab" data-toggle="pill"
								data-target="#v-pills-home" type="button" role="tab"
								aria-controls="v-pills-home" aria-selected="true"
								href="addArt.jsp">我的文章</button>
							<button class="nav-link" id="v-pills-profile-tab"
								data-toggle="pill" data-target="#v-pills-profile" type="button"
								role="tab" aria-controls="v-pills-profile" aria-selected="false">我的收藏</button>
							
							<br> <a class="btn btn-primary" href="addArt.jsp"
								role="button" border-radius: 20px>新增文章</a>

						</div>
					</div>
					<div class="col-9">
						<div class="tab-content" id="v-pills-tabContent">
							<div class="tab-pane fade show active" id="v-pills-home"
								role="tabpanel" aria-labelledby="v-pills-home-tab">

								<table class="table table-striped">
									<thead>

										<tr>
											<th scope="col"></th>
											<th scope="col">文章標題</th>
											<th scope="col">文章分類</th>
											<th scope="col">發布時間</th>
											<th scope="col">最後編輯時間</th>
											<th scope="col">修改</th>
											<th scope="col">刪除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="artVO" items="${list}">
											<tr>
												<th scope="row"></th>
												<td><a href="<%=request.getContextPath()%>/front_end/art/art.do?action=getOne_article&articleId=${artVO.articleId}">${artVO.articleTitle}</a></td>
												<jsp:useBean id="artTypeSvc" scope="page"
													class="com.arttype.model.ArtTypeService" />
												<td>${artVO.artTypeVO.articleTypeName}</td>
												<td>${artVO.articlePostTime}</td>
												<td>${artVO.articleUpdateTime}</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/front_end/art/art.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改"> <input
															type="hidden" name="articleId" value="${artVO.articleId}">
														<input type="hidden" name="action"
															value="getOne_For_Update">
													</FORM>
												</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/front_end/art/art.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="刪除"> <input
															type="hidden" name="articleId" value="${artVO.articleId}">
														<input type="hidden" name="action" value="delete">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
							<div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
								aria-labelledby="v-pills-profile-tab">
								<table class="table" ">
										<thead>
											<tr>
												<th class="text-center">分類-文章名稱</th>

												<th class="text-center">作者</th>
												<th class="text-center">最後編輯時間</th>
												<th class="text-center">收藏</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="favoriteArtVO" items="${favoriteArtList}">
												<tr id="item1">
													<td><a href="<%=request.getContextPath()%>/front_end/art/art.do?action=getOne_article&articleId=${favoriteArtVO.articleId}"><strong>[${favoriteArtVO.getTheArticleType().articleTypeName}] <br> ${favoriteArtVO.getTheArticle().articleTitle}</strong></a>
													
													
													<td class="text-center">${favoriteArtVO.getTheArticle().memberId}</td>
													<td class="text-center">${favoriteArtVO.getTheArticle().articleUpdateTime}</td>
													<td class="text-center">
														<c:if test="${empty mem }">
															<button class="inactive-btn" id="heartBtn">
																<i class="fas fa-heart"></i>
															</button>
														</c:if>
														<c:if test="${not empty mem }">
														<input type="hidden" name="memberId" id="ArticleMemId" value="${sessionScope.mem.memberId}"> 
															<input type="hidden" name="articleId" id="ArticleId" value="${favoriteArtVO.articleId}">
														<button class="heartBtn ${(favoriteArtSvc.getTheFavoriteArt(favoriteArtVO.articleId, mem.memberId)!=null) ? 'action-btn': 'inactive-btn'}" >
																<i class="fas fa-heart" style="width:18px; height:18px"></i> 
														</button>
					<!-- 									$(this).find('input[name=articleId]').val() -->
					<!-- 									prev() -->
					<!-- 									next() -->
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>


									</table>

							</div>
							
						</div>
					</div>
				</div>

				<br>
				<!--請注意: 以上填入你要寫的東西 結束-->

			</section>
	</main>


	<footer data-background="../assets/img/bg/footer.jpg">
		<div class="footer-area pt-5">
			<div class="container">
				<div class="row d-flex justify-content-center ">
					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<div class="footer-logo mb-25">
								<img src="../assets/img/logo/Mumu_logo.png" alt="">
							</div>
							<div class="social-icon d-flex justify-content-center">
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous">
		
	</script>


	<!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
	<script src="../assets/js/dropdown.js"></script>
	<script>
		$(document)
				.ready(
					$('.heartBtn').on('click', function(e) {
					e.preventDefault();
					console.log(123);
					
					<% if(request.getSession()==null || request.getSession().getAttribute("mem") == null){%>
						window.location.href = 'http://localhost:8081/CGA105G4_0207/front_end/login.jsp';
					<%}else{%>
								
					$(this).toggleClass('action-btn');
					$(this).toggleClass('inactive-btn');
								
					console.log($('input[name=articleId]').val());
					let articleId = $('input[name=articleId]').val();
					articleId = $(this).prev().val();
					console.log(articleId);	
					
					$.ajax({ 
						url:  'http://localhost:8081/CGA105G4_0207/front_end/art/Favoriteart.do',
						type: 'POST',
// 						data: 'action=addArticleToFavorList&articleId=2',
						data: 
							{action: 'addArticleToFavorList',articleId: articleId}

							
						}).then(function(res){
							console.log('收藏成功');
						}, function(err){
							console.log(err);
						});
						window.location.href = 'http://localhost:8081/CGA105G4_0207/front_end/art/artManage.jsp'
					<%}%>
							})
					
						);
		
	</script>
	

</body>
</html>