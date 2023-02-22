<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminaccessmanage.model.*"%>
<%@ page import="com.admin.model.*"%>


<%
	AdminService adminSvc = new AdminService();
	List<AdminVO> list = adminSvc.getAll();
	pageContext.setAttribute("list", list);
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
					<h1 class="mt-4">管理員列表</h1>
					<div class="container">
						<div class="row align-items-end">

							
						</div>
					</div>

					<div class="card mb-4">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>管理員ID</th>
									<th>管理員名稱</th>
									<th>帳號</th>
									<th>權限項目</th>
									<th>編輯</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="adminVO" items="${list}">
									<tr>
										<th scope="row">${adminVO.adminId}</th>
										<td>${adminVO.adminAccountName}</td>
										<td>${adminVO.adminAccount}</td>
										
										<td >
										<c:if test="${fn:contains(adminVO.adminAcess, '1')}">
											[ 交流區 ]
										</c:if>
										<c:if test="${fn:contains(adminVO.adminAcess, '2')}">
											[ 募資 ]  
										</c:if>
										<c:if test="${fn:contains(adminVO.adminAcess, '3')}">
											[ 商城 ] 
										</c:if>
										<c:if test="${fn:contains(adminVO.adminAcess, '4')}">
											[ 審核 ] 
										</c:if>
										<c:if test="${fn:contains(adminVO.adminAcess, '5')}">
											[ 查看會員 ] 
										</c:if>
										<c:if test="${fn:contains(adminVO.adminAcess, '6')}">
											[ 管理會員 ] 
										</c:if>
										<c:if test="${fn:contains(adminVO.adminAcess, '7')}">
											[ 管理後台人員 ] 
										</c:if>
										</td>
										
										<td>
										<c:if test="${fn:contains(sessionScope.admin.adminAcess, '7')}">
											 <form method="post" action="<%=request.getContextPath()%>/back_end/adminaccessmanage/accessManage">
											<input type="hidden" name="adminId" value="${adminVO.adminId}">
											<input type="hidden" name="action" value="ready_update">
											<input class="btn btn-warning" type="submit" value="編輯權限">
											</form>
										</c:if>
										</td>
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