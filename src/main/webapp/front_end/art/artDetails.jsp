<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.arttype.model.*"%>
<%@ page import="com.art.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="com.comment.model.CommentVO"%>
<%@page import="com.comment.model.CommentService"%>
<%@ page import="com.comment.model.*"%>



<%
ArtTypeService artTypeSvc = new ArtTypeService();
List<ArtTypeVO> artTypelist = artTypeSvc.getAll();
pageContext.setAttribute("artTypelist", artTypelist);
%>
<%
ArtVO artVO = (ArtVO) request.getAttribute("artVO");
pageContext.setAttribute("artVO", artVO);
%>


<%
String location = request.getContextPath()+ "/front_end/art/art.do?action=getOne_article&articleId=" + artVO.getArticleId();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);

%>

<%
ArtTypeVO artTypeVO = (ArtTypeVO) session.getAttribute("artTypeVO");
%>

<%
ArtService articleSvc = new ArtService();
List<ArtVO> articlelist = articleSvc.getAll();
pageContext.setAttribute("articlelist", articlelist);
%>

<%
CommentVO commentVO = (CommentVO) request.getAttribute("commentVO");
%>

<%
artVO = (ArtVO) session.getAttribute("artVO");
pageContext.setAttribute("artVO", artVO);
ArtVO artVO2 = (ArtVO)request.getAttribute("artVO");
// pageContext.getAttribute("articleId", artVO.getArticleId());
%>

<%
CommentService commentSvc = new CommentService();
List<CommentVO> list = commentSvc.getAll();
pageContext.setAttribute("list", list);

// List<CommentVO> list = (List<CommentVO>)request.getAttribute("list");
// pageContext.setAttribute("list", list);
%>



<!DOCTYPE html>
<html class="no-js" lang="en">
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">

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
<style>

.fa-heart { 
border: 0px solid black;
padding: 0;
background-color: lightgray;
height:auto;
weight:auto;
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

		<section class="grey-bg pt-120 pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->

				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="art.jsp">交流區首頁</a></li>
					<li class="breadcrumb-item active" aria-current="page">文章</li>
				</ol>

				<!-- blog-area start -->
<%-- 				<c:forEach items="${articlelist}" var="artVO"> --%>
				<div class="blog-area pt-0 pb-80">
					<div class="container">
						<div class="row">
							<div class="col-lg-8">
								<article class="postbox post format-image mb-40">

									<div class="my-5">
										<div class="postbox__text bg-none">

											<div class="post-meta mb-15">

												<span><i class="far fa-calendar-check"></i>${artVO.articlePostTime}
												</span>

											</div>

											<h3 class="blog-title">
												<a ${artVO.articleTitle}>${artVO.articleTitle}</a>
											</h3>
											<div class="post-text mb-20">
												<p>${artVO.articleContent}</p>
												<!--                                         
                                    </div>                                   
                                   
                                     </div>
                                    <!--以上文章新增區 -->
												<div class="row mt-50">
													<div class="col-xl-8 col-lg-8 col-md-8 mb-15">
														<div class="blog-post-tag">
															
														</div>
													</div>
													<div class="col-xl-4 col-lg-4 col-md-4 mb-15">
													<jsp:useBean id="favoriteArtSvc" class="com.favoriteart.model.FavoriteArtService" scope="page" />
														
														<div class="blog-share-icon text-left text-md-right">
														<c:if test="${empty sessionScope.mem}">
															<div style="display: inline; margin-right: 15px;"> 		
																<button class="heartBtn action-btn" style="border: 0px; padding: 0px; background-color: white; height:14px; top-left:3px"  >
																		<i class="fas fa-heart" style="width:18px; height:18px"></i>            
																</button>
															</div> 
														</c:if>
														<c:if test="${not empty sessionScope.mem}">
															<div style="display: inline; margin-right: 15px;"> 											
															<input type="hidden" name="memberId" id="ArticleMemId" value="${sessionScope.mem.memberId}"> 
															<input type="hidden" name="articleId" id="ArticleId" value="${artVO.articleId}">
<%-- 															<input type="hidden" name="articleId" id="ArticleId" value="${articleId}"> --%>
															<button class="heartBtn ${(favoriteArtSvc.getTheFavoriteArt(artVO.articleId, sessionScope.mem.memberId)!=null) ? 'inactive-btn' : 'action-btn'}" style="border: 0px; padding: 0px; background-color: white; height:14px; top-left:3px"  >
																	<i class="fas fa-heart" style="width:18px; height:18px"></i>            
															</button>
<%-- 															<button class="heartBtn ${(favoriteArtSvc.getTheFavoriteArt(artVO2.articleId, sessionScope.mem.memberId)!=null) ? 'inactive-btn' : 'action-btn'}" style="border: 0px; padding: 0px; background-color: white; height:14px; top-left:3px"  > --%>
<!-- 																	<i class="fas fa-heart" style="width:18px; height:18px"></i>             -->
<!-- 															</button> -->
															</div> 
														</c:if>
															<div style="display: inline; margin-right: 10px">
<!-- 															<a href="#"><i class="fa-regular fa-heart"></i></a>  -->
															
															<a href="#"><i class="fa-regular fa-thumbs-up"></i></a>
															</div> 
															<div style="display: inline">
															<a href="#"><i class="fa-sharp fa-regular fa-star"></i></a> 
<!-- 															</span> -->
 															</div> 
 															
														</div>
														
													</div>
												</div>
												<div class="row">
													<div class="col-12">
														<div class="navigation-border pt-50 mt-40"></div>
													</div>
													
												</div>
											</div>

											<div class="post-comments">
												<div class="blog-coment-title mb-30">
													<h2>留言版</h2>
												</div>
												<div class="latest-comments">


													<c:forEach var="commentVO" items="${list}">
													
													
													<c:if test="${commentVO.articleId == artVO.articleId}">
<%-- 														<%=request.getContextPath()%>/front_end/art/Comment.do?articleId=${commentVO.articleId }&action=getarticleId" --%>
														<div class="comments-box">															
															<div class="media">
																<div class="media-body">
																	<h5 class="mt-0 mb-1">${commentVO.memVO.memberName}</h5>
																	<span  >${commentVO.commentPostTime}</span>
																	<p>${commentVO.commentContent}</p>
																</div>
																<img src="<%=request.getContextPath()%>/ImageServlet?commentId=${commentVO.commentId}"
																width=100px height=100px class="ml-3" onerror="this.style='display: none'">
															</div>
														</div>
														</c:if>													 
														</c:forEach>

												</div>
											</div>
											<div class="post-comments-form">
												<div class="post-comments-title">
													<h2>留言區</h2>
												</div>
												<FORM method="post" id="contacts-form"
													class="conatct-post-form"
													action="<%=request.getContextPath()%>/front_end/art/Comment.do"
													enctype="multipart/form-data">
													<input type="hidden" name="articleId"
														value="${artVO.articleId }">
													<div class="row">

														<div class="col-xl-12">
															<div class="contact-icon contacts-message">
																<textarea name="commentContent" id="comments"
																	value="<%=(commentVO == null) ? "請輸入留言內容" : commentVO.getCommentContent()%>"
																	cols="30" rows="10" placeholder="你的留言...."></textarea>
															</div>
														</div>
														<div class="form-group">
															<label for="exampleFormControlFile1">新增圖片 </label> <input
																type="file" name="commentpic" multiple="multiple">
														</div>

														<br>
														<div class="col-xl-12">

															<input type="hidden" name="action" value="insert">
															<input class="btn btn-primary" type="submit" value="提交留言">
														</div>
													</div>
												</FORM>
											</div>
								</article>
							</div>
							<div class="col-lg-4">
								<div class="widget mb-40">
									<div class="widget-title-box mb-30">
										<span class="animate-border"></span>
										<h3 class="widget-title">文章搜尋</h3>
									</div>

									<FORM METHOD="post" ACTION="art.do" class="search-form">
										<input type="text" name="article" placeholder="請輸入文章關鍵字">
										<input type="hidden" name="action" value="articlekey">
										<button type="submit" value="送出">
											<i class="fas fa-search"></i>
										</button>
									</FORM>
								</div>
								<div c lass="widget mb-40">
									<div class="widget-title-box mb-30">
										<span class="animate-border"></span>
										<h3 class="widget-title">文章分類</h3>
									</div>
									<ul class="cat">

										<c:forEach var="artTypeVO" items="${artTypelist}">
											<li><a
												href="<%=request.getContextPath() %>/front_end/art/ArtTypelist.do?articleTypeId=${artTypeVO.articleTypeId }&action=getArtlist">${artTypeVO.articleTypeName}
													<span class="f-right"></span>
											</a></li>
										</c:forEach>

									</ul>
								</div>
								<br>
								<div class="widget mb-40">
									<div class="widget-title-box mb-30">
										<span class="animate-border"></span>
										<h3 class="widget-title">Social Profile</h3>
									</div>

									<div class="social-profile">
										<a href="https://www.facebook.com/sharer.php?u＝網址"><i
											class="fab fa-facebook-f"></i></a> <a
											href="https://twitter.com/share?text=文字&url=網址"><i
											class="fab fa-twitter"></i></a> <a
											href="http://line.naver.jp/R/msg/text/?網址"><i
											class="fab fa-linkedin-in"></i></a> <a href><i
											class="fab fa-youtube"></i></a>
									</div>
								</div>

<!-- 								<div class="widget mb-40 p-0 b-0"> -->
<!-- 									<div class="banner-widget"> -->
<!-- 										<a href="#"><img -->
<!-- 											src="../assets/img/blog/details/banner.jpg" alt=""></a> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>
						</div>
					</div>
				</div>
				<!-- blog-area end -->
<%-- 				</c:forEach> --%>
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
					
					<%}%>
							})
					
						);
	</script>
	
</body>
</html>