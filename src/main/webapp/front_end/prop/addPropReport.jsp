<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prop.model.*"%>
<%@ page import="com.propopt.model.*"%>

<c:if test="${empty sessionScope.mem}">
	<jsp:forward page="../login.jsp"/>
</c:if>

<%
PropOptVO propOptVO = (PropOptVO) request.getAttribute("propOptVO");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>新增檢舉提案</title>
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
<link rel="stylesheet" href="../assets/css/common.css">


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

		<section class="grey-bg pb-120">
			<div class="container">
				<!--請注意: 以下填入你要寫的東西 開始-->
				<body>
					<div class="form-wrapper">
						<h1 class="mb-4">提交檢舉</h1>
						<form METHOD="post" ACTION="propReport.do" name="form1"
							>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<label class="input-group-text" for="inputGroupSelect01">提案名稱</label>
								</div>
								<select class="form-control" id="inputGroupSelect01"
									name="proposalId" required>
									<option disabled selected value="">Choose...</option>
									<jsp:useBean id="propSvc" scope="page"
										class="com.prop.model.PropService" />
									<c:forEach var="propVO" items="${propSvc.all}">
										<option value="${propVO.proposalId}"
											${(propOptVO.proposalId==propVO.proposalId)? 'selected':'' }>${propVO.proposalName}</option>
									</c:forEach>
								</select>
								<div class="invalid-feedback">
                    請選擇提案
                </div>
							</div>
							<div class="form-floating">
								<textarea name="reportCause" class="form-control"
									placeholder="xxx" pattern="(/^\S+$/)" maxlength="30" required></textarea>
								<label>檢舉原因</label>
								<div class="invalid-feedback">不可空白</div>
							</div>

							<div class="d-flex justify-content-center">
								 <input type="submit"
									class="btn btn-default" name="action" value="送出申請">
							</div>

						</form>

						<script
							src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
					</div>

				</body>


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
	<script src="../assets/js/picture.js"></script>
		<script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});

			//Below code to check every input field
			var inputs = document.getElementsByClassName('form-control');
			var input_Validation = Array.prototype.filter.call(inputs,
					function(input) {

						input.addEventListener('blur', function(event) {

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

</body>

</html>