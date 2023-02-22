<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<style>
.form-control {
	border-radius: 0.25rem !important;
	margin-left: 5px;
}

.t-heading {
	color: #727272;
}

.propCollectionItem td {
	color: #212121;
	font-weight: 400;
}

.delBtn {
	cursor: pointer;
	width: fit-content;
}

.delBtn:hover {
 	color: #9f9fa1 !important; 
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
		<section class="grey-bg pt-60 pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->

				<div class="prop-collection_wrapper">
					<h2 class="prop-collection-table_title">我的收藏提案</h2>
					<table class="table table-hover" id="transactions">
						<thead>
							<tr role="row" class="t-heading">
								<th class="col-sm-2"></th>
								<th class="col-sm-4">收藏專案</th>

								<th class="col-sm-1">募資進度</th>
								<th class="col-sm-2">募資結束期限</th>
								<th class="col-sm-2">募資狀態</th>
								<th class="col-sm-1"></th>
							</tr>
						</thead>
						<tbody class="propCollectionList">
							<c:forEach var="propCollection" items="${propCollectionList }">
								<tr class="propCollectionItem">

									<td><img
										src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${propCollection.proposalID}"
										alt="" class="popularImg img-fuid"
										onerror="this.src='<%=request.getContextPath()%>/front_end/assets/img/image_not_available.png'" /></td>
									<td><a
										href="<%=request.getContextPath() %>/front_end/prop/prop.jsp?prop=${propCollection.getProposalID()}"
										target="_blank"
										style="color: #f8414a; font-size: 16px; font-weight: 400;">${propCollection.propVO.proposalName }</a></td>

									<td>${100 * propCollection.propVO.accumulativeDonateMoney/propCollection.propVO.targetDonateMoney}%</td>
									<td>${String.format("%1$TF",propCollection.propVO.proposalEndDatetime) }</td>
									<td class="numberFont"
										style="font-weight: 500; font-size: 14px;">${(propCollection.propVO.proposalStatus <=2)?'未達標':'已達標，結束募資' }</td>
									<td><div class="delBtn" style="color: #d3d3d5"><i class="fa-solid fa-x"></i><form><input type="hidden" name="action" value="deleteLikes"/><input type="hidden" name="proposalId" value="${propCollection.proposalID }"/><input type="hidden" name="memberId" value="${mem.memberId }"/></form></div></td>

								</tr>

							</c:forEach>
							<!-- survey Form -->


						</tbody>
					</table>
				</div>



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
		$(document).ready(function() {
	
			$('.numberFont').each(
					function() {
						if ($(this).text() == '未達標'
								&& parseInt($(this).prev().prev()
										.text()) >= 100) {
							$(this).text('已達標');
						}
					});
					
			
			$('.delBtn').on('click', function(){
				console.log("Delete hit");
				
				console.log($(this).find('form').serialize());
				
				let formData = $(this).find('form').serialize();
				let url = '<%=request.getContextPath()%>/propcollection/propcollection.do';
				
				
				fetch(url, {
				    method: "POST",
				    body: formData,
				    headers: {
				    	"Content-Type": "application/x-www-form-urlencoded"
				    }
				}).then(function(res){
					console.log('移除成功');
				}).catch(function(err){
					console.log(err);
				});
				
				//移除 node
				$(this).parents('tr').remove();
				    
			})
		});
	</script>


</body>
</html>