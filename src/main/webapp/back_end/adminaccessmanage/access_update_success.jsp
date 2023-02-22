<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminaccessmanage.model.*"%>
<%@ page import="com.admin.model.*"%>


<%
	AdminVO adminVO = (AdminVO)request.getAttribute("adminVO");
	AdminAccessManageService aamSvc = new AdminAccessManageService();
	List<AdminAccessManageVO> list = aamSvc.getOneAdmin(adminVO.getAdminId());

	pageContext.setAttribute("adminVO",adminVO);
	pageContext.setAttribute("list",list);
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
					<h1 class="mt-4">已經將 <span style="color: blue;">${adminVO.adminAccountName}</span> 的權限項目保存成功!</h1>
					<div class="container">
						<div class="row align-items-end">
							<table class="table">
								<td>以下為 <span style="color: blue;">${adminVO.adminAccountName}</span> 擁有的全部權限項目。</td>
								
								<td align="right"><a	href="<%=request.getContextPath()%>/back_end/adminaccessmanage/admin_access.jsp"
									class="btn btn-success" role="button">回列表</a></td>
							
							</table>

						</div>
					</div>

					<div class="card mb-4">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>人員ID:${adminVO.adminId}</th>
									<th>人員名稱:${adminVO.adminAccountName}</th>
									<th>所屬帳號:${adminVO.adminAccount}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="adminAcessManageVO" items="${list}">
									<tr>
										<th scope="row">⚫</th>
										<td colspan="2" align="center">${adminAcessManageVO.accessFuncVO.accessFunctionName}</td>
										
									</tr>
								</c:forEach>

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