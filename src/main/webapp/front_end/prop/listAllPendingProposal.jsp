<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prop.model.*"%>
<%@ page import="com.propopt.model.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.mem.model.*"%>
<%
HttpSession Asession = request.getSession();
String location = (request.getQueryString() == null)
? request.getRequestURL().toString()
: request.getRequestURL().append("?").append(request.getQueryString()).toString();

Asession.setAttribute("location", location);
%>

<c:if test="${empty sessionScope.mem}">
	<jsp:forward page="../login.jsp"/>
</c:if>
<%
PropOptService propOptSvc = new PropOptService();
List<PropOptVO> listOpt = propOptSvc.getAll();
pageContext.setAttribute("listOpt", listOpt);
%>


<%
int currentUserId = ((MemVO)request.getSession().getAttribute("mem")).getMemberId();
List<PropVO> list = new PropService().getAll().stream()
    .filter(propVO -> propVO.getMemberId() == currentUserId)
    .collect(Collectors.toList());
pageContext.setAttribute("list", list);

%>



<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>提案紀錄</title>
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
                        <h3>&nbsp; &nbsp; &nbsp; 提案管理<img id="logo-icon" src="../images/proposal-logo.png">
                            </h3>
                            <!-- <i class='bx bxs-bulb bx-flip-horizontal' style='color:#e8ec7f'></i> -->
                            <!-- <i class='bx bx-bulb'></i> -->
                    </header>
                    <nav>
                        <button onclick="location.href='<%=request.getContextPath()%>/front_end/prop/funding_proposal_management.jsp'">
                        
                            <span>
                     
                                &nbsp;<img src="../images/proposal-funding.png">
                                <span>&nbsp; &nbsp;未達標提案 </span>
                            	
                            </span>
                          
                        </button>
                        <button onclick="location.href='<%=request.getContextPath()%>/front_end/prop/addProposalToShop2.jsp'">
                            <span>
                                &nbsp;<img id="u2standard-img" src="../images/search.png" class="logo-img">
                                <span>&nbsp; &nbsp;已達標提案 </span>
                            </span>
                        </button>
                        
                        
                        <button onclick="location.href='<%=request.getContextPath()%>/front_end/prop/listAllPendingProposal.jsp'">
                            <span>
                                &nbsp;<img src="../images/proposal-record.png">
                                <span>&nbsp; &nbsp;提案紀錄</span>
                            </span>
                        </button>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                    </nav>
                </aside>
            </div>
			<div class="content col-8">
				<section>
					<div style="text-align: center;">
						<h3>提案紀錄</h3>
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
								<th scope="col">審核狀態</th>
								<th scope="col">提案狀態</th>
								<th scope="col">募資開始時間</th>
								<th scope="col">募資結束時間</th>
								<th scope="col">目標金額</th>
								<th scope="col">累積募資金額</th>
								
								
								<th scope="col">提案詳細資料</th>
							</tr>
						</thead>

						<%@ include file="page/page1.file"%>
						<c:forEach var="propVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">


							<tbody>
		<tr>
								<th scope="row">${propVO.proposalId}</th>
										<td>${propVO.proposalName}</td>
										<td>${propVO.proposalApprovalStatus}</td>
										<td>${propVO.proposalStatus}</td>
										<td>${propVO.proposalStartDatetime}</td>
										<td>${propVO.proposalEndDatetime}</td>
										<td>${propVO.targetDonateMoney}</td>
										<td>${propVO.accumulativeDonateMoney}</td>

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
																	<th>提案選項編號</th>
																	<th>提案選項名稱</th>
																	<th>提案選項價格</th>
																
																	
																	
																</tr>
															</thead>
															<tbody>
																<tr>

																	
																	<c:forEach var="propOptVO" items="${listOpt}">
																		<c:if
																			test="${propOptVO.proposalId == propVO.proposalId}">
																			<tr>
																				<td>${propOptVO.proposalOptionId}</td>
																				<td>${propOptVO.proposalOptionName}</td>
																				<td>${propOptVO.proposalOptionPrice}</td>
																			</tr>
																		</c:if>

																	</c:forEach>

																</tr>
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
								</tr>

							</tbody>

						</c:forEach>
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