<!-- 目前最新都用這個檔案 -->
<%@page import="com.prodtype.model.ProdTypeVO"%>
<%@page import="com.prodtype.model.ProdTypeService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--需要裝兩個檔案才不會出現錯誤 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderlist.model.*"%>



<%
String location = (request.getQueryString() == null)
		? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);
%>

<%
ProdTypeService prodTypeSvc = new ProdTypeService();
List<ProdTypeVO> prodTypeList = prodTypeSvc.getAll();

pageContext.setAttribute("prodTypeList", prodTypeList);
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
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<title>所有訂單資料</title>

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
</style>



</head>
<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>

				<div class="container my-5 mx-4">

					<!--=========================請注意: 寫你要的內容 -->
					<h3 class="mb-3">新增商品</h3>

					<form action="<%=request.getContextPath() %>/AddProductServlet"  method="post" enctype="multipart/form-data">
						<input type="hidden" name="action" value="addProduct">
						<div class="form-group">
							<label for="productType">商品分類</label> 
							<select
								class="form-control" id="productType" name="productType">
								<c:forEach var="propTypeVO" items="${prodTypeList }">
									<option value="${propTypeVO.productTypeId }">${propTypeVO.productTypeName }</option>
								
								</c:forEach>

							</select>
						</div>

						<div class="form-group">
							<label for="productName">商品名稱</label> <input type="name"
								class="form-control" id="productName" name="productName"
								placeholder="Enter productName">
						</div>
						<div class="form-group">
							<label for="productDescription">商品描述</label>
							<textarea class="form-control" id="productDescription"
								name="productDescription" rows="4"></textarea>
						</div>

						<div class="form-group mb-4">
							<label for="productPicture">商品圖片</label> <br>
							<input type="file"
								class="form-control-file" id="productPicture" name="productPicture">
						</div>

						<button type="submit" class="btn btn-primary">新增商品</button>
					</form>

				</div>

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
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"
		integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="../assets/demo/chart-area-demo.js"></script>
	<script src="../assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>

	<script>
		$('.custom-select').on(
				'change',
				function() {
					$(this).parents('td').next().find('.orderStatus').val(
							$(this).val());
					// 											$(this).prev('orderIdValue').val()
				})
	</script>
</body>
</html>