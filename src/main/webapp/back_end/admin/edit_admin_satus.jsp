<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminaccessmanage.model.*"%>
<%@ page import="com.admin.model.*"%>


<%
	AdminVO adminVO = (AdminVO)request.getAttribute("adminVO");
	AdminService adminSvc = new AdminService();
	byte adminAccountStatus = adminVO.getAdminAccountStatus();

	pageContext.setAttribute("adminVO",adminVO);
	pageContext.setAttribute("adminAccountStatus",adminAccountStatus);
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
<style type="text/css">
.onoffswitch {
    position: relative; width: 90px;
    -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
}
.onoffswitch-checkbox {
    position: absolute;
    opacity: 0;
    pointer-events: none;
}
.onoffswitch-label {
    display: block; overflow: hidden; cursor: pointer;
    border: 2px solid #999999; border-radius: 20px;
}
.onoffswitch-inner {
    display: block; width: 200%; margin-left: -100%;
    transition: margin 0.3s ease-in 0s;
}
.onoffswitch-inner:before, .onoffswitch-inner:after {
    display: block; float: left; width: 50%; height: 30px; padding: 0; line-height: 30px;
    font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
    box-sizing: border-box;
}
.onoffswitch-inner:before {
    content: "ON";
    padding-left: 10px;
    background-color: #03C8FF; color: #FFFFFF;
}
.onoffswitch-inner:after {
    content: "OFF";
    padding-right: 10px;
    background-color: #EEEEEE; color: #999999;
    text-align: right;
}
.onoffswitch-switch {
    display: block; width: 18px; margin: 6px;
    background: #FFFFFF;
    position: absolute; top: 0; bottom: 0;
    right: 56px;
    border: 2px solid #999999; border-radius: 20px;
    transition: all 0.3s ease-in 0s; 
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
    margin-left: 0;
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
    right: 0px; 
}
</style>
</head>

<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">您正在編輯 <span style="color: blue;">${adminVO.adminAccountName}</span> 的帳號狀態。</h1>
					<div class="container">
						<div class="row align-items-end">
							<table class="table">
							<tr>
								<td align="right">
								<a	href="<%=request.getContextPath()%>/back_end/admin/admin_list.jsp" class="btn btn-success" role="button">回列表</a>
								</td>
							</tr>
							</table>
							
						</div>
					</div>

					<div class="card mb-4">
						<table class="table table-hover">
						<form method="post"	action="<%=request.getContextPath()%>/back_end/admin/admin.do">
						
						
							<thead>
								<tr>
									<th>管理員ID</th>
									<th>管理員名稱</th>
									<th>帳號</th>
									<th >狀態</th>
									<th ></th>
								</tr>
							</thead>
							<tbody>
								<tr>
								
									<th scope="row">${adminVO.adminId}</th>
									<td>${adminVO.adminAccountName}</td>
									<td>${adminVO.adminAccount}</td>
									<td>
										<div class="onoffswitch">
										    <input type="checkbox" name="status" value="1" class="onoffswitch-checkbox" id="myonoffswitch" tabindex="0" ${(adminAccountStatus != 0)? 'checked' : '' }>
										    <label class="onoffswitch-label" for="myonoffswitch">
										        <span class="onoffswitch-inner"></span>
										        <span class="onoffswitch-switch"></span>
										    </label>
										</div>
									</td>
									<td align="right">
												<input type="hidden" name="adminId" value="${adminVO.adminId}">
												<input type="hidden" name="action" value="save">
												<button type="submit" class="btn btn-warning">保存</button>
											
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