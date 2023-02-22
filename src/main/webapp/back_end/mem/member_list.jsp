<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>


<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
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
					<h1 class="mt-4">MUMU會員列表</h1>
					<div class="container">
						<div class="row align-items-end">
							<table class="table">
							<tr>
								<td align="right">
<%-- 								<form method="post" action="<%=request.getContextPath()%>"> --%>
<!-- 									<button type="submit" class="btn btn-primary">查詢會員</button> -->
<!-- 								</form> -->
								</td>
							</tr>
							</table>

						</div>
					</div>

					<div class="card mb-4">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>會員ID</th>
									<th>會員名稱</th>
									<th>性別</th>
									<th>帳號</th>
									<th>狀態</th>
									<th>地址</th>
									<th>
									<c:if test="${fn:contains(sessionScope.admin.adminAcess, '6')}">
									編輯狀態
									</c:if>
									</th>
									<th>查看詳細資料</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="MemVO" items="${list}">
									<tr>
										<th scope="row">${MemVO.memberId}</th>
										<td>${MemVO.memberName}</td>
										<td>${MemVO.memberGender}</td>
										<td>${MemVO.memberAccount}</td>
										<td>${(MemVO.memberStatus == 1)? '<span style="color:blue;">正常</span>' : '<span style="color:red;">停用</span>'}</td>
										<td>${MemVO.memberAddress}</td>

										<td>
										<c:if test="${fn:contains(sessionScope.admin.adminAcess, '6')}">
											<form method="post"	action="<%=request.getContextPath()%>/back_end/mem/member.do">
												<input type="hidden" name="memberId" value="${MemVO.memberId}">
												<input type="hidden" name="action" value="edit_status">
												<button type="submit" class="btn btn-warning">編輯狀態</button>
											</FORM>
										</c:if>
										</td>
										<td >
											<form method="post"	action="<%=request.getContextPath()%>/back_end/mem/member.do">
												<input type="hidden" name="memberId" value="${MemVO.memberId}">
												<input type="hidden" name="action" value="getOne">
												<button type="submit" class="btn btn-warning">詳細資料</button>
											</FORM>
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