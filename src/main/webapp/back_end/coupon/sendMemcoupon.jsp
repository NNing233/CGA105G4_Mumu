<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ page import="com.memcoupon.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
String location = (request.getQueryString() == null) ? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);
%>
<!DOCTYPE html>
<%
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>
<%
MemCouponVO memCouponVO = (MemCouponVO) request.getAttribute("memCouponVO");
%>
<%
MemService memSvc = new MemService();
List<MemVO> mem = memSvc.getAll();
pageContext.setAttribute("mem", mem);
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
<link href="<%=request.getContextPath()%>/back_end/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>優惠券新增</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>



</head>
<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>

				<!--=========================請注意: 寫你要的內容 -->
				<table id="table-2">
					<tr>
						<td>
							<h3>新增優惠券持有會員</h3>
						</td>
					</tr>
				</table>

				<table>
					<%-- 					<%@ include file="page1.file"%> --%>

					<%-- 					<c:forEach var="memVO" items="${mem}" begin="<%=pageIndex%>" --%>
					<%-- 						end="<%=pageIndex+rowsPerPage-1%>"> --%>
					<tr>
						<%-- 							<c:forEach var="memVO" items="${mem}"> --%>
						<c:if test="${memVO.memberID == memCouponVO.memberID}">
							<tr>
								<td>${memVO.memberID}</td>
							</tr>
						</c:if>
						<%-- 							</c:forEach> --%>


					</tr>


					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/memCoupon/memCoupon.do">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="memType"
									id="flexRadioDefault1" value="all" checked> <label
									class="form-check-label" for="flexRadioDefault1"> 所有會員
								</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="memType"
									id="flexRadioDefault1" value="male"> <label
									class="form-check-label" for="flexRadioDefault1"> 所有男會員
								</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="memType"
									id="flexRadioDefault2" value="female"> <label
									class="form-check-label" for="flexRadioDefault2"> 所有女會員
								</label>
							</div>

							<input type="hidden" name="couponId" value="${param.MemHascoupon}">
							<input type="hidden" name="action" value="insert"> <input
								type="submit" value="送出新增">
						</form>

					</td>
				</table>
				<br>
				</FORM>

				<!--=========================請注意: 結束要的內容 -->

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
	<script src="js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/back_end/assets/demo/chart-area-demo.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>
</body>
</html>