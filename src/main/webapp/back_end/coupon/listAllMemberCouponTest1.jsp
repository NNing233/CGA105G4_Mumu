<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--需要裝兩個檔案才不會出現錯誤 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.memcoupon.model.*"%>

<%
String location = (request.getQueryString() == null) ? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);
%>
<!DOCTYPE html>
<%
CouponService orderSvc = new CouponService();
List<CouponVO> list = orderSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
MemCouponService memCouponSvc = new MemCouponService();
List<MemCouponVO> memCoupon = memCouponSvc.getAll();
pageContext.setAttribute("memCoupon", memCoupon);
%>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/back_end/css/styles.css"
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<title>所有人的優惠券</title>

<style>
table#table-1 {
	background-color: white;
	/* 	border: 2px solid black; */
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
/* .AAA {display: flex;  justify-content: center;} */
</style>


</head>

<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>

				<!-- 請注意: 寫你要的內容 -->
				<div class="container ">

					<div class="AAA">

						<table id="table-1">
							<tr>
									<h3>所有優惠券</h3>
							</tr>
						</table>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>優惠券名稱</th>
									<th>優惠券折數</th>
									<th>優惠券折讓</th>
									<th>開始時間</th>
									<th>結束時間</th>
									<th>修改優惠券</th>
									<th>發送優惠券</th>
									<th>持有會員</th>
								</tr>
							</thead>

							<%@ include file="page1.file"%>

							<c:forEach var="couponVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
									<%-- 								<td>${couponVO.couponID}</td> --%>
									<td>${couponVO.couponName}</td>
									<td>${couponVO.discountPercentage}</td>
									<td>${couponVO.discountAmount}</td>
									<td><fmt:formatDate
											value="${couponVO.couponStartDatetime}"
											pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${couponVO.couponEndDatetime}"
											pattern="yyyy/MM/dd HH:mm:ss" /></td>


									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back_end/coupon/coupon.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改"> <input type="hidden"
												name="couponID" value="${couponVO.couponID}"> <input
												type="hidden" name="action" value="update_data">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back_end/coupon/coupon.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="發送"> <input type="hidden"
												name="MemHascoupon" value="${couponVO.couponID}"> <input
												type="hidden" name="action" value="sendMemCoupon_Data">
										</FORM>
									</td>

									<td>
										<!-- Button to Open the Modal -->

										<button type="button" class="btn btn-outline-primary"
											data-bs-toggle="modal"
											data-bs-target="#myModal_${couponVO.couponID}">詳細資料</button>

										<div class="modal" id="myModal_${couponVO.couponID}">
											<div class="modal-dialog modal-xl">
												<div class="modal-content">

													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title">優惠券詳細資料</h4>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal"></button>
													</div>

													<!-- Modal body -->

													<div class="modal-body">
														<table class="table table-striped">
															<thead>
																<tr>
																	<th>會員編號</th>
																	<th>優惠券折扣碼</th>
																	<th>優惠券狀態</th>
																</tr>
															</thead>
															<tbody>

																<c:forEach var="memCouponVO" items="${memCoupon}">
																	<c:if
																		test="${memCouponVO.couponID == couponVO.couponID}">
																		<tr>
																			<td>${memCouponVO.memberID}</td>
																			<td>${memCouponVO.couponCodeNumber}</td>
																			<td>${memCouponVO.couponStatus}</td>
																		</tr>
																	</c:if>

																</c:forEach>


															</tbody>
														</table>
													</div>

													<!-- Modal footer -->
													<div class="modal-footer">
														<button type="button" class="btn btn-danger"
															data-bs-dismiss="modal">關閉</button>
													</div>

												</div>
											</div>
										</div>

									</td>

								</tr>
							</c:forEach>
						</table>

						<%@ include file="page2.file"%>

					</div>
				</div>
				<!-- <h1>測試測試</h1> -->

			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2022</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/back_end/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-area-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/js/datatables-simple-demo.js"></script>
</body>

</html>