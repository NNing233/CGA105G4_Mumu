<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>

<%@ page import="com.mem.model.*"%>


<%
	MemVO memVO = (MemVO)request.getAttribute("memVO");
	pageContext.setAttribute("memVO",memVO);
%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Charts - SB Admin</title>
<link href="<%=request.getContextPath()%>/back_end/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">您正在查看 <span style="color: blue;">${memVO.memberName}</span> 的詳細資料。</h1>
					<div class="container">
						<div class="row align-items-end">
							<table class="table">
							<tr>
								<td></td>
<!-- 圖片位置	 -->							<td></td>
								<td align="right">
								<a	href="<%=request.getContextPath()%>/back_end/mem/member_list.jsp" class="btn btn-success" role="button">回列表</a>
								</td>
							</tr>
							</table>
						</div>
					</div>

					<div class="card mb-4">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>會員ID:${memVO.memberId}</th>
									<th>會員名稱:${memVO.memberName}</th>
									<th >會員性別:${memVO.memberGender}</th>
									<th >帳號:${memVO.memberAccount}</th>
									<th align="right">狀態:${(memVO.memberStatus == 1)? '<span style="color:blue;">正常</span>' : '<span style="color:red;">停用</span>'}</th>
								</tr>
							</thead>
							<tbody>
									<tr>
										<th scope="row" >會員暱稱:</th>
										<td colspan="3" align="center">${memVO.memberNickname}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row" >會員生日:</th>
										<td colspan="3" align="center">${memVO.dateOfBirth}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row" >身分證字號:</th>
										<td colspan="3" align="center">${memVO.idNumber}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row" >聯絡信箱:</th>
										<td colspan="3" align="center">${memVO.memberEmail}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row" >聯絡電話:</th>
										<td colspan="3" align="center">${memVO.memberPhone}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row">通訊地址:</th>
										<td colspan="3" align="center">${memVO.memberAddress}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row" >銀行帳號:</th>
										<td colspan="3" align="center">${memVO.bankAccount}</td>
										<td></td>
									</tr>
									<tr>
										<th scope="row" >註冊時間:</th>
										<td colspan="3" align="center">${memVO.signUpDatetime}</td>
										<td></td>
									</tr>
							</tbody>
						</table>

					</div>
				</div>
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
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-area-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-bar-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-pie-demo.js"></script>
</body>

</html>