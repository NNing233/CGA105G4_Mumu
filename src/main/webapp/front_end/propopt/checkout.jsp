<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
System.out.println("Session" + request.getSession(false));
if (request.getSession(false) == null || request.getSession(false).getAttribute("mem") == null) {
	response.sendRedirect(request.getContextPath() + "/front_end/prop/index.jsp");
}
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



<style>
label {
	color: #212529;
	font-size: 14px;
}

.purchaseInfo button {
	padding: 5px 14px;
	background-color: transparent;
	color: #000 !important;
	font-size: 16px;
}

.purchaseInfo button:active {
	color: #FFF !important;
}

.purchaseInfo button:hover {
	background-color: transparent;
	color: black;
}

.purchaseInfo .proposalOptionQuantity {
	font-size: 20px;
}

.labelTitle {
	font-size: 14px;
	color: #212529;
}

.btn-outline-success:hover {
	color: #fff !important;
    background-color: #28a745 !important;
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

		<section class="pt-40 pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->

				<form id="sponsorForm" method="post" action="<%=request.getContextPath() %>/sponrecord/sponrecord.do" class="p-4 w-100 needs-validation"
					novalidate>

					<h4>贊助方案</h4>
					<div class="purchaseSection d-flex mb-4">

						<div class="proposalOptionImage mb-3 mr-3">
							<img style="max-width: 160px;"
								src="<%=request.getContextPath()%>/propopt/propopt.do?action=showImg&proposalOptionId=${propOpt.proposalOptionId}"
								onerror="this.src='<%=request.getContextPath()%>/front_end/assets/img/image_not_available.png'"
								alt="" />
						</div>

						<div class="purchaseInfo d-flex flex-column">
							<h5 class="proposalOptionName">${propOpt.proposalOptionName }</h5>


							<div class="bottomDiv">
								<div class="input-group mb-3" style="flex-wrap: nowrap">
									<div class="input-group-prepend">
										<button class="btn btn-outline-secondary substractBtn"
											style="height: 42px; border: solid 1px lightgray; border-right: none; border-radius: 10px; border-top-right-radius: 0; border-bottom-right-radius: 0;"
											type="button" disabled>-</button>

									</div>
									<input type="text" class="proposalOptionQuantity"
										name="proposalOptionQuantity" placeholder="" aria-label=""
										aria-describedby="basic-addon1"
										style="height: 42px; text-align: center; border: solid 1px lightgray; border-left: none; border-right: none;"
										value=1 pattern="[0-9]+" readonly required>

									<div class="input-group-prepend">
										<button class="btn btn-outline-secondary addBtn" type="button"
											style="height: 42px; border: solid 1px lightgray; border-left: none; border-radius: 10px; border-top-left-radius: 0; border-bottom-left-radius: 0;">+</button>
									</div>
								</div>
								<div class="priceBox">
									<div class="priceHint">單價 x 數量&ensp; = 小計</div>
									<div class="priceHint">
										$<span class="proposalOptionPrice">${propOpt.proposalOptionPrice }</span>
										x &ensp;<span class="proposalOptionQuantityCopy">1</span>&emsp;&ensp;
										= &ensp;<span style="color: black; font-size: 30px;">NT
											$</span><span class="sponsorTotalAmount"
											style="color: black; font-size: 30px;">${propOpt.proposalOptionPrice }</span>
									</div>
								</div>
							</div>

						</div>
					</div>

					<!-- 					個人付款資訊 -->

					<input class="js-demeter-tw-zipcode-selector d-none"
						data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號" /> <input
						type="hidden" name="memberId" value="${mem.memberId }" /> <input
						type="hidden" name="proposalOptionId"
						value="${propOpt.proposalOptionId }" /> 
						<input type="hidden" name="proposalId" value="${propOpt.proposalId }" />
						<input type="hidden" name="fullAddress" />
						<input type="hidden" name="proposalOptionPrice" value="${propOpt.proposalOptionPrice }"/>
						<input type="hidden" name="action" value="createSponsorRecord" />
						





					<div class="infoSection">
						<h4>訂購人資訊</h4>


						<div class="form-row">
							<div class="form-group col-12">
								<label for="memberName"> 姓名</label> <input class="form-control"
									type="text" id="memberName" placeholder="${mem.memberName }"
									readonly>
							</div>
							<div class="form-group col-12">
								<label for="phone"> 手機</label> <input class="form-control"
									type="text" id="phone" placeholder="${mem.memberPhone }"
									readonly>
							</div>
							<div class="form-group col-sm-6">
								<label for="payment"><i class="fa-solid fa-wallet"></i>
									付款方式</label> <select class="form-control" name="payment" id="payment"
									placeholder="請選擇金流" required>
									<option value>請選擇金流</option>
									<option value="1">信用卡</option>
									<option value="2">轉帳</option>
								</select>
								<div class="invalid-feedback">請選擇金流</div>
							</div>

							<div class="form-group col-sm-6">
								<label for="delivery"><i class="fa-solid fa-truck"></i>
									運送方式</label> <select class="form-control" name="delivery" id="delivery"
									placeholder="請選擇物流" required>
									<option value>請選擇物流</option>
									<option value="1">黑貓宅急便</option>
									<option value="2">新竹物流</option>
									<option value="3">嘉里大榮</option>
								</select>
								<div class="invalid-feedback">請選擇物流</div>
							</div>
							<div class="form-group col-12 d-none" id="creditCard">
								<label for="ccn">信用卡號</label> <input class="form-control"
									id="ccn" type="tel" inputmode="numeric" pattern="[0-9]{16}"
									maxlength="16" placeholder="xxxx xxxx xxxx xxxx">
								<div class="invalid-feedback">請輸入正確信用卡號</div>
							</div>
						</div>





						<div class="labelTitle">
							<i class="fa-solid fa-house"></i> 收件人地址
						</div>
						<div class="form-row">

							<div class="form-group col-sm-3">
								<select class="form-control" name="city" id="city"
									placeholder="請選擇縣市" required></select>
								<div class="invalid-feedback">請選擇縣市</div>
							</div>
							<div class="form-group col-sm-3">
								<select id="dist" class="form-control" name="dist"
									placeholder="請選擇鄉鎮區" required></select>
								<div class="invalid-feedback">請選擇縣鎮區</div>
							</div>
							<div class="form-group col-12">
								<input id="address" class="form-control" name="address"
									placeholder="街、路、號、樓" required></select>
								<div class="invalid-feedback">地址請勿留白</div>
							</div>
						</div>

						<div class="form-group mb-3">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="invalidCheck" required> <label
									class="form-check-label" for="invalidCheck"> 我同意<a
									href="yahoo.com.tw" target="_blank">網站服務條款</a>及<a
									href="yahoo.com.tw" target="_blank">隱私權條款</a>
								</label>
								<div class="invalid-feedback">You must agree before
									submitting.</div>
							</div>
						</div>

						<button type="submit" class="btn btn-outline-success"
							style="color: #28a745; background-color: transparent; background-image: none; border-color: #28a745; display: inline-block; font-weight: 600; text-align: center; white-space: nowrap; vertical-align: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; border: 1px solid transparent; padding: 0.7rem 1.75rem; font-size: 1rem; line-height: 1.5; border-radius: 0.25rem; transition: color .15s; border: solid 1.5px #28a745; height: auto">立即贊助</button>
					</div>
				</form>
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
				// Example starter JavaScript for disabling form submissions if there are invalid fields
				(function() {
					'use strict';
					window.addEventListener('load', function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						
						const addBtn = $('.addBtn');
						const substractBtn = $('.substractBtn');
						const proposalOptionQuantityInput = $('.proposalOptionQuantity');
						const proposalOptionQuantityCopy = $('.proposalOptionQuantityCopy');
						const sponsorTotalAmount = $('.sponsorTotalAmount');
						const proposalOptionPrice = $('.proposalOptionPrice');
						
						// +-按鈕 對 proposalOptionQuantity
						addBtn.on('click', ()=> {
							proposalOptionQuantityInput.val(+proposalOptionQuantityInput.val() + 1);
							proposalOptionQuantityCopy.text(proposalOptionQuantityInput.val());
							sponsorTotalAmount.text(+proposalOptionQuantityInput.val() * +proposalOptionPrice.text());
							
							proposalOptionQuantityInput.trigger('change');
						});
						
						substractBtn.on('click', ()=> {
							proposalOptionQuantityInput.val(+proposalOptionQuantityInput.val() - 1);
							proposalOptionQuantityCopy.text(proposalOptionQuantityInput.val());
							sponsorTotalAmount.text(+proposalOptionQuantityInput.val() * +proposalOptionPrice.text());
							
							proposalOptionQuantityInput.trigger('change');
							console.log(123222);
						});

						proposalOptionQuantityInput.on('change', ()=> {
							
							if(proposalOptionQuantityInput.val() == "1") {
								substractBtn.prop('disabled', true);
							} else {
								substractBtn.prop('disabled', false);
							}
						});
						
						//信用卡欄位 顯示與否
						if($('#payment').val() == "1") {
							$('#creditCard').removeClass('d-none');
						}
						
						$('#payment').on('change', function() {
							if($(this).val() == "1") {
								//信用卡
								$('#creditCard').removeClass('d-none');
							} else {
								$('#creditCard').addClass('d-none');
							}
						});
						
						//製作 fullAddress 完整地址
						$('#sponsorForm').on('submit', function(e) {
							
							$("input[name='fullAddress']").val(123);
							let cityName = $('#city').val();
							let distName = $('#dist').val();
							let address = $('#address').val();

							$("input[name='fullAddress']").val(cityName + distName + address);

						})
						
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});

						//Below code to check every input field
						var inputs = document
								.getElementsByClassName('form-control');
						var input_Validation = Array.prototype.filter.call(
								inputs, function(input) {

									input.addEventListener('blur', function(
											event) {

										input.classList.remove('is-invalid')
										input.classList.remove('is-valid')

										if (input.checkValidity() === false) {
											input.classList.add('is-invalid')
										} else {
											input.classList.add('is-valid')
										}
										// form.classList.add('was-validated');

									}, false);
								});

						//End of block that checks each input field

					}, false);
				})();

				$("#account").on('blur', function() {
					console.log('處理驗證');
				})
			</script>
	<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
</body>

</html>