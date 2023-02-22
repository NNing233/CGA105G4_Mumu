<%@page import="com.artcarousel.model.ArtCarouselVO"%>
<%@page import="com.artcarousel.model.ArtCarouselService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.artcarousel.model.*"%>
<%@page import="com.arttype.model.*"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>


<%
    ArtCarouselService ArtCarouselSvc = new ArtCarouselService();
    List<ArtCarouselVO> list = ArtCarouselSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%
    String location = (request.getQueryString()==null)? request.getRequestURL().toString() :request.getRequestURL().append("?").append(request.getQueryString()).toString();
    HttpSession session1 = request.getSession();
    session1.setAttribute("location", location);
%>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
        <div id="layoutSidenav_content">
            <main>

                <!-- 請注意: 寫你要的內容 -->
                <div class="container-fluid px-4">
                    <h1 class="mt-4">輪播圖管理</h1>
                    <div class="card mb-4">
                        <div class="card-body">
                            <a target="_blank" href="addArtCarousel.jsp"">新增輪播圖</a>
                        </div>
                    </div>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                           輪播圖管理
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>交流區輪播圖編號</th>
                                        <th>交流區輪播圖號(位置)</th>
                                        <th>分類編號</th>
                                        <th>圖片</th>
                                        <th>連結</th>
                                        <th>修改</th>
                                        <th>刪除</th>
                                    </tr>
                                </thead>
                                <!-- <tfoot>
                                    <tr>
                                        <th>交流區輪播圖編號</th>
                                        <th>交流區輪播圖號(位置)</th>
                                        <th>分類編號</th>
                                        <th>圖片</th>
                                        <th>連結</th>
                                        <th>修改</th>
                                        <th>刪除</th>
                                    </tr>
                                </tfoot> -->
                                <tbody>
<%--                                <%@ include file="page1.file"%> --%>
							   <c:forEach var="artCarouselVO" items="${list}">
                                    <tr>
                                        <td>${artCarouselVO.articleCarouselId}</td>
                                        <td>${artCarouselVO.articleCarouselPosition}</td>
                                        <td>${artCarouselVO.artTypeVO.articleTypeId}</td>
<%--                                    <td>${artCarouselVO.artTypeVO.articleTypename}</td> --%>
                                        <td><img src ="<%=request.getContextPath()%>/ImageServlet?articleCarouselId=${artCarouselVO.articleCarouselId}"width=300px height=200px></td>
                                        <td>${artCarouselVO.articleLink}</td>
                                        <td>
                                        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/artcarousel.do" style="margin-bottom: 0px;">
			     							<input type="submit" value="修改">
			     							<input type="hidden" name="articleCarouselId"  value="${artCarouselVO.articleCarouselId}">
			     							<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                                        </td>
                                        <td>
                                        	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/artcarousel.do" style="margin-bottom: 0px;">
			     							<input type="submit" value="刪除">
			     							<input type="hidden" name="articleCarouselId"  value="${artCarouselVO.articleCarouselId}">
			     							<input type="hidden" name="action" value="delete"></FORM>
                                        </td>
                                    </tr>

                                    </c:forEach>
<%--                                     <%@ include file="page2.file" %> --%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- <h1>測試測試</h1> -->

            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Your Website 2022</div>
                        <div>
                            <a href="#">Privacy Policy</a>
                            &middot;
                            <a href="#">Terms &amp; Conditions</a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>

</html>