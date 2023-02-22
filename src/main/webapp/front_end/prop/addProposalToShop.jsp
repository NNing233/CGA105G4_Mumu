<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prop.model.*"%>
<%@ page import="com.propopt.model.*"%>
<%@ page import="com.sponrecord.model.*"%>
<%@ page import="java.util.stream.Collectors"%>


<%
PropService propSvc = new PropService();
List<PropVO> list = propSvc.getAll();
pageContext.setAttribute("list", list);

List<PropVO> filteredList = list.stream()
.filter(propVO -> propVO.getProposalStatus() == ProposalStatus.已上架未達標)
.collect(Collectors.toList());
pageContext.setAttribute("filteredList", filteredList);
%>


<%
PropOptService propOptSvc = new PropOptService();
List<PropOptVO> listOpt = propOptSvc.getAll();
pageContext.setAttribute("listOpt", listOpt);
%>


<%
SponRecordService sponRecordSvc = new SponRecordService();
List<SponRecordVO> listSponRecord = sponRecordSvc.getAll();
pageContext.setAttribute("listSponRecord", listSponRecord);
%>

<%
PropVO propVO = (PropVO) request.getAttribute("propVO");
%>


<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>選擇欲上架至商城的提案</title>
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
			<!-- Button trigger modal -->
			<div class="side col-3">
				<aside class="sidebar">
					<header class="sidebar-header">
						<div style="text-align: center;">
							<h4>已達標提案</h4>
						</div>
					</header>
					<nav>
						<button>
							<span> &nbsp;<img src="../images/proposal-logo.png">
								<span>&nbsp; &nbsp;未達標提案</span>
							</span>
						</button>
						<button>
							<span> &nbsp;<img id="u2standard-img"
								src="../images/search.png" class="logo-img"> <span>&nbsp;
									&nbsp;已達標提案</span>
							</span>
						</button>
						<button>
							<span> &nbsp;<img src="../images/proposal-u2shop.png">
								<span>&nbsp; &nbsp;已上架商城</span>
							</span>
						</button>
						<button>
							<span> &nbsp;<img src="../images/proposal-record.png">
								<span>&nbsp; &nbsp;提案紀錄</span>
							</span>
						</button>
						<br> <br> <br> <br> <br>
					</nav>
				</aside>
			</div>
			<div class="content col-8">
				<section>
					<div style="text-align: center;">
						<h3>已達標提案紀錄</h3>
					</div>
				</section>
				<hr style="border: 1px solid">
				<section>

					<!--請注意: 以下填入你要寫的東西 開始-->
					<!-- Button trigger modal -->

					<table class="table table-hover">

						<thead>
							<tr>

								<th scope="col">提案編號</th>
								<th scope="col">提案名稱</th>
								<th scope="col">提案狀態</th>
								<th scope="col">詳細資料</th>
								<th scope="col">查看贊助者</th>
								<th scope="col">更新提案狀態</th>
								<th scope="col">上架至商城</th>
							</tr>
						</thead>

						<%@ include file="page/page3.file"%>
						<tbody>
							<c:forEach var="propVO" items="${filteredList}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								
										<th scope="row">${propVO.proposalId}</th>
										<td>${propVO.proposalName}</td>
										<td>${propVO.proposalStatus}</td>

										<td>
											<!-- Button to Open the Modal -->

											<button type="button" class="btn btn-primary"
												data-bs-toggle="modal"
												data-bs-target="#myModal_${propVO.proposalId}">詳細資料</button>

											<div class="modal" id="myModal_${propVO.proposalId}">
												<div class="modal-dialog modal-xl">
													<div class="modal-content">

														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">提案詳細資料</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>

														<!-- Modal body -->

														<div class="modal-body">
															<table class="table table-striped">
																<thead>
																	<tr>
																		<th>提案</th>
																		
																	</tr>
																</thead>
																<tbody>

																	<c:forEach var="propOptVO" items="${listOpt}">
																		<c:if
																			test="${propOptVO.proposalId == propVO.proposalId}">
																			<tr>
																				<td>${propOptVO.proposalOptionName}</td>
																			</tr>
																		</c:if>

																	</c:forEach>


																</tbody>
															</table>
														</div>

														<!-- Modal footer -->
														<div class="modal-footer">
															<button type="button" class="btn btn-danger"
																data-bs-dismiss="modal">Close</button>
														</div>

													</div>
												</div>
											</div>


										</td>

										<td>
											<!-- Button to Open the Modal -->

											<button type="button" class="btn btn-primary"
												data-bs-toggle="modal"
												data-bs-target="#myModal_1${propVO.proposalId}">查看贊助者</button>

											<div class="modal" id="myModal_1${propVO.proposalId}">
												<div class="modal-dialog modal-xl">
													<div class="modal-content">

														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">查看贊助者</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>

														<!-- Modal body -->

														<div class="modal-body">
															<table class="table table-striped">
																<thead>
																	<tr>
																		<th>查看贊助者</th>
																	</tr>
																</thead>
																<tbody>

																	<c:forEach var="sponRecordVO" items="${listSponRecord}">
																		<c:if
																			test="${sponRecordVO.proposalId == propVO.proposalId}">
																			<tr>
																				<td>${sponRecordVO.memberId}</td>
																			</tr>
																		</c:if>

																	</c:forEach>

																</tbody>
															</table>
														</div>

														<!-- Modal footer -->
														<div class="modal-footer">
															<button type="button" class="btn btn-danger"
																data-bs-dismiss="modal">Close</button>
														</div>

													</div>
												</div>
											</div>


										</td>

										<td>

											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<label class="input-group-text" for="inputGroupSelect01">提案狀態</label>
												</div>
												<select class="custom-select" id="PropStatus"
													name="propStatus">
													<option selected>Choose...</option>

													<option
														value="4">已達標生產階段</option>
													<option
														value="5">已達標產品寄送</option>
													<option
														value="6">已達標違信退款</option>
													<option
														value="7">結案</option>
												</select> <input type="hidden" name="action" value="insert">
												<input type="submit" value="修改">
												</div>
									
									
									</td>
										
								<td><form method="post" action="selectPropProdReview.do">
								<input type="hidden" name="proposalId" value="${propVO.proposalId}">
										<input type="submit" class="btn btn-success"
											name="toShop" value="申請上架"></form></td> 
											
								
									</tr>
	





							</c:forEach>
						</tbody>
						<%@ include file="page/page2.file"%>
					</table>




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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous">
		
	</script>


	<!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
	<script src="../assets/js/dropdown.js"></script>
	<script>
		document.getElementById("processbar").setAttribute("style",
				"width: 50%;");
	</script>
</body>

</html>