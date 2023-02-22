<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.sponrecord2.model.*"%>

<%
//System.out.println("Session" + request.getSession(false));
//if (request.getSession(false) == null || request.getSession(false).getAttribute("mem") == null) {
//	response.sendRedirect(request.getContextPath() + "/front_end/prop/index.jsp");
//}
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

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>
.form-control {
	border-radius: 0.25rem !important;
	margin-left: 5px;
}

.t-heading {
	color: #727272;
}

.sponsorItem td {
	color: #212121;
	font-weight: 400;
}

.btn-outline-primary {
	color: #007bff;
	background-color: transparent;
	background-image: none;
	border-color: #007bff;
	border-radius: 7px;
	font-size: 14px;
}

.btn-outline-primary:focus {
	color: #007bff;
}

.btn-outline-primary:hover {
	color: #fff;
}

.btn-outline-danger {
	color: #dc3545;
	background-color: transparent;
	background-image: none;
	border-color: #dc3545;
	font-size: 12px;
	border-radius: 7px;
}

.btn-outline-danger:hover {
	background-color: #dc3545;
	color: #fff !important;
}

.btn-outline-danger:focus {
	color: #dc3545;
}

.btn-primary {
	color: #fff;
	background-color: #007bff;
	border-color: #007bff;
	display: inline-block;
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	border: 1px solid transparent;
	padding: 0.375rem 1.5rem;
	font-size: 1rem;
	line-height: 1.5;
	border-radius: 0.25rem;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	height: 50px;
}
</style>
</head>

<body>
	<!-- preloader -->
	<!-- 	<div id="preloader"> -->
	<!-- 		<div class="preloader"> -->
	<!-- 			<span></span> <span></span> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- preloader end  -->

	<%@ include file="/front_end/header/header.jsp"%>

	<main>

		<section class="grey-bg pt-60 pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->


				<div class="record-table_wrapper">
					<h2 class="record-table_title">你的贊助</h2>
					<table class="table table-hover" id="transactions">
						<thead>
							<tr role="row" class="t-heading">
								<th class="col-sm-1"></th>
								<th class="col-sm-3">贊助專案</th>
								<th class="col-sm-1">贊助數量</th>
								<th class="col-sm-3">回饋項目</th>
								<th class="col-sm-1">贊助總金額</th>

								<th class="col-sm-1">訂單狀態</th>
								<th class="col-sm-1"></th>
								<th class="col-sm-1"></th>
							</tr>
						</thead>
						<tbody class="recordlist">
							<c:forEach var="sponsorRecordVO" items="${sponsorRecordList }">
								<tr class="sponsorItem">

									<td><img
										src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${sponsorRecordVO.proposalId}"
										alt="" class="popularImg img-fuid"
										onerror="this.src='<%=request.getContextPath()%>/front_end/assets/img/image_not_available.png'" /></td>
									<td><a
										href="<%=request.getContextPath() %>/front_end/prop/prop.jsp?prop=${sponsorRecordVO.proposalId}"
										target="_blank"
										style="color: #f8414a; font-size: 16px; font-weight: 400;">${sponsorRecordVO.propVO.proposalName }</a></td>

									<td>${sponsorRecordVO.proposalOptionQuantity }</td>
									<td>${sponsorRecordVO.propOptVO.proposalOptionName }
										<p class="sponsorDate grey small">下單時間：${sponsorRecordVO.sponsorDatetime }</p>
									</td>
									<td class="numberFont"
										style="font-weight: 500; font-size: 18px;">$${sponsorRecordVO.sponsorTotalAmount
										}</td>
									<td>${ SponRecordStatus.getStatusName(sponsorRecordVO.sponsorRecordStatus) }</td>

									<td><c:if
											test="${sponsorRecordVO.sponsorRecordStatus < 3}">
											<button class="btn btn-outline-primary modifyBtn">修改</button>
										</c:if></td>
									<td><c:if
											test="${sponsorRecordVO.sponsorRecordStatus == 1}">
											<form action="<%=request.getContextPath() %>/sponrecord/sponrecord.do" method="post">
												<input type="hidden" name="sponsorRecordId" value="${sponsorRecordVO.sponsorRecordId }"/>
												<input type="hidden" name="sponsorTotalAmount" value="${sponsorRecordVO.sponsorTotalAmount}"/>
												<input type="hidden" name="proposalId" value="${sponsorRecordVO.proposalId}"/>
												<input type="hidden" name="action" value="cancelSponsorRecord"/>
												
												
												<button class="btn btn-outline-danger cancelSponsor" type="submit">取消贊助</button>
											</form>
										</c:if></td>

								</tr>


								<tr class="surveyForm" style="display: none;">
									<td colspan="8" class="surveyContent">
										<div class="row py-2 px-4">
											<ul class="hity small grey" style="padding: 10px 20px;">
												<li>為了順利遞送專案的回饋，請務必確實填寫，這些資訊將用於專案後續聯絡。</li>
												<li><span style="color: red">* </span>收件人姓名/手機號碼/信箱屬於會員中心的資料，如需修改，<a
													href="" style="text-decoration: underline; color: #007bff">請至會員中心的個人頁面編輯</a></li>
											</ul>


											<form method="POST"
												action="<%=request.getContextPath()%>/sponrecord/sponrecord.do"
												accept-charset="UTF-8" class="recipientForm w-100">
												<input name="sponsorRecordId" type="hidden"
													value="${sponsorRecordVO.sponsorRecordId }"> <input
													type="hidden" name="action"
													value="modifySponsorRecordWithAddress" />

												<div class="col-12 sendyData">
													<div class="disableBox"></div>
													<p class="bluequote">
														寄送資訊 <small class="grey"
															style="font-weight: 300; margin-left: 12px;">以下為贊助人聯絡資料，請確實填寫</small>
													</p>

													<div class="form-group">
														<div class="input-group">
															<div class="input-group-addon clabel">收件人姓名</div>
															<input class="form-control" type="text"
																value="${sponsorRecordVO.memVO.memberName}" readonly>
														</div>
													</div>

													<div class="form-group">
														<div class="input-group">
															<div class="input-group-addon clabel">手機號碼</div>
															<input class="form-control" type="tel"
																value="${sponsorRecordVO.memVO.memberPhone}" readonly>
														</div>
													</div>

													<div class="form-group">
														<div class="input-group">
															<div class="input-group-addon clabel">聯絡信箱</div>
															<input class="form-control" type="email"
																value="${sponsorRecordVO.memVO.memberEmail}" readonly>
														</div>
													</div>

													<div class="form-group">
														<div class="input-group">
															<div class="input-group-addon clabel">收件地址</div>
															<input class="form-control" name="address" type="text"
																value="${sponsorRecordVO.sponsorRecordAddress}" required>
														</div>
													</div>







												</div>


												<div class="col-12">
													<input class="btn btn-primary updateForm" type="submit"
														value="確認修改">
												</div>

											</form>
										</div>

									</td>
								</tr>
							</c:forEach>
							<!-- survey Form -->


						</tbody>
					</table>
				</div>







				<!-- 				<table class="table"> -->
				<!-- 					<thead> -->
				<!-- 						<tr> -->
				<!-- 							<th scope="col">#</th> -->
				<!-- 							<th scope="col">First</th> -->
				<!-- 							<th scope="col">Last</th> -->
				<!-- 							<th scope="col">Handle</th> -->
				<!-- 						</tr> -->
				<!-- 					</thead> -->
				<!-- 					<tbody> -->
				<!-- 						<tr> -->
				<!-- 							<th scope="row">1</th> -->
				<!-- 							<td>Mark</td> -->
				<!-- 							<td>Otto</td> -->
				<!-- 							<td>@mdo</td> -->
				<!-- 						</tr> -->
				<!-- 						<tr> -->
				<!-- 							<th scope="row">2</th> -->
				<!-- 							<td>Jacob</td> -->
				<!-- 							<td>Thornton</td> -->
				<!-- 							<td>@fat</td> -->
				<!-- 						</tr> -->
				<!-- 						<tr> -->
				<!-- 							<th scope="row">3</th> -->
				<!-- 							<td>Larry</td> -->
				<!-- 							<td>the Bird</td> -->
				<!-- 							<td>@twitter</td> -->
				<!-- 						</tr> -->
				<!-- 					</tbody> -->
				<!-- 				</table> -->

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
		$('.modifyBtn').on('click', function() {
			$(this).parents('tr').next('.surveyForm').toggle();

		});
	</script>

	<c:if test="${param.modify == 'success'}">
		<script>
			Swal.fire('修改成功', '', 'success');
		</script>
	</c:if>
	
	<c:if test="${param.cancelSponsor == 'success'}">
		<script>
			Swal.fire('贊助已取消', '', 'success');
		</script>
	</c:if>
	
	<script type="text/javascript">

		
		$('.cancelSponsor').on('click', function(e) {
			e.preventDefault();
			var form = $(this).parents('form');
			Swal.fire({
				title : "確定要取消贊助嗎",
				icon : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "確定取消",
				cancelButtonText: '抱歉，我按錯了',
				closeOnConfirm : false
				}).then((result) => {
				  if (result.isConfirmed) {
					  	form.submit();
					  }
				});
		});
	</script>
</body>

</html>