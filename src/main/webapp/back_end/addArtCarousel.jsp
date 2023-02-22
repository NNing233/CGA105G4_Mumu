<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.artcarousel.model.*"%>
<%@ page import="java.util.*"%>

<%
ArtCarouselVO artCarouselVO = (ArtCarouselVO) request.getAttribute("artCarouselVO");
%>

<%
    String location = (request.getQueryString()==null)? request.getRequestURL().toString() :request.getRequestURL().append("?").append(request.getQueryString()).toString();
    HttpSession session1 = request.getSession();
    session1.setAttribute("location", location);
%>


<!-- line 100 -->

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
<link href="<%=request.getContextPath()%>/back_end/css/styles.css"
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">

			<main>
			
				<!-- 請注意: 寫你要的內容 -->

				<h5>新增輪播圖</h5>
				
				<p>
					<a href="listArtCarousel.jsp" font-size=8px>回輪播圖清單</a>
				<table id="table-1">
				
				<FORM METHOD="post" ACTION="artcarousel.do" name="form1"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>圖片位置編號:</td>
							<td><input class="form-control" type="TEXT" name="articleCarouselPosition"
								size="45"
								value="<%=(artCarouselVO == null) ? "" : artCarouselVO.getArticleCarouselPosition()%>" /><br></td>
						</tr>

						<tr>
							<td>請選擇文章分類:</td>
							<td><select class="form-control" name="articleTypeId"name="articleTypeId">
							<option selected>請選擇</option>
								<jsp:useBean id="artTypeSvc" scope="page"
									class="com.arttype.model.ArtTypeService" />
									<c:forEach var="artTypeVO" items="${artTypeSvc.all}">
										<option value="${artTypeVO.articleTypeId}"
										${(artVO.articleId==artTypeVO.articleTypeId)? 'selected':'' }>${artTypeVO.articleTypeName}
									</option>
									</c:forEach>
							</select><br></td>
						</tr>

						<tr>
							<td>連結:</td>
							<td><input class="form-control"  type="TEXT" name="articleLink" size="45"
								value="<%=(artCarouselVO == null) ? "" : artCarouselVO.getArticleLink()%>" /><br></td>
						</tr>

						<tr>
							<td>新增圖片:</td>
							<td><input type="file" name="upfile1" ><br></td>
							
						</tr>

					</table>
					<br> <input type="hidden" name="action" value="insert">
					<input type="submit" class="btn btn-primary" value="送出新增" >
					
				</FORM>


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