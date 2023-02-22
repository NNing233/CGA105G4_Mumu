<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminaccessmanage.model.*"%>
<%@ page import="com.admin.model.*"%>


<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
Integer[] adminAccess = (Integer[]) request.getAttribute("adminAccess");

pageContext.setAttribute("adminVO", adminVO);
pageContext.setAttribute("adminAccess", adminAccess);
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
	
	<style>
	
	
	
	</style>


</head>

<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">
						您正在編輯 <span style="color: blue;">${adminVO.adminAccountName}</span>
						的權限
					</h1>
					<div class="container">
						<div class="row align-items-end">
							<div class="col"></div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col">
							<a	href="<%=request.getContextPath()%>/back_end/adminaccessmanage/admin_access.jsp" class="btn btn-success" role="button">回列表</a>
							</div>
							<div class="col">
							<form method="post"	action="<%=request.getContextPath()%>/back_end/adminaccessmanage/accessManage">
								<input type="hidden" name="adminId" value="${adminVO.adminId}">
								<input type="hidden" name="action" value="ready_update">
								<button class="btn btn-danger" type="submit" >重置</button>
							</form>
							</div>
							<div class="col">
									<button form="form1" class="btn btn-warning" type="submit" >保存</button>
							</div>
							
						</div>
					</div>

					<div class="card mb-4">
						<form id="form1" method="post" action="<%=request.getContextPath()%>/back_end/adminaccessmanage/accessManage">
							<input type="hidden" name="adminId" value="${adminVO.adminId}">
							<input type="hidden" name="action" value="update">
							<table class="table table-hover">

								<thead>
									<tr>
										<th>人員ID:${adminVO.adminId}</th>
										<th>人員名稱:${adminVO.adminAccountName}</th>
										<th>所屬帳號:${adminVO.adminAccount}</th>

									</tr>
								</thead>
							
								<tbody>
									
									<tr>
										<th scope="row">⚫</th>
										<td>交流區</td>
										<td align="center"><input type="checkbox" name="oneForum"
											value="1"  ${(adminAccess[0] != null)? 'checked' : '' } />
											
										</td>

									</tr>
									<tr>
										<th scope="row">⚫</th>
										<td>募資</td>
										<td align="center"><input type="checkbox" name="towProposal"
										 value="2" 	${(adminAccess[1] != null)? 'checked' : '' } />
										   
										</td>
									</tr>
									<tr>
										<th scope="row">⚫</th>
										<td>商城</td>
										<td align="center"><input type="checkbox"
											name="threeShop" value="3"
											${(adminAccess[2] != null)? 'checked' : '' } />
										</td>
									</tr>
									<tr>
										<th scope="row">⚫</th>
										<td>審查</td>
										<td align="center"><input type="checkbox"
											name="fourReport" value="4"
											${(adminAccess[3] != null)? 'checked' : '' } />
										</td>
									</tr>
									<tr>
										<th scope="row">⚫</th>
										<td>查看會員</td>
										<td align="center"><input type="checkbox"
											name="fiveMember" value="5"
											${(adminAccess[4] != null)? 'checked' : '' } />
										</td>
									</tr>
									<tr>
										<th scope="row">⚫</th>
										<td>管理會員</td>
										<td align="center"><input type="checkbox"
											name="sixMember" value="6"
											${(adminAccess[5] != null)? 'checked' : '' } />
										</td>
									</tr>
									<tr>
										<th scope="row">⚫</th>
										<td>管理後台人員</td>
										<td align="center"><input type="checkbox"
											name="sevenAdmin" value="7"
											${(adminAccess[6] != null)? 'checked' : '' } />
										</td>
									</tr>
								</tbody>
							

							</table>
							
						</form>
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