<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminaccessmanage.model.*"%>
<%@ page import="com.admin.model.*"%>


<%

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
.login-block {
    margin: 30px auto;
    min-height: 93.6vh
}

.login-block .auth-box {
    margin: 20px auto 0;
    max-width: 450px !important
}

.card {
    border-radius: 5px;
    -webkit-box-shadow: 0 0 5px 0 rgba(43, 43, 43, .1), 0 11px 6px -7px rgba(43, 43, 43, .1);
    box-shadow: 0 0 5px 0 rgba(43, 43, 43, .1), 0 11px 6px -7px rgba(43, 43, 43, .1);
    border: none;
    margin-bottom: 30px;
    -webkit-transition: all .3s ease-in-out;
    transition: all .3s ease-in-out;
        background-color: #fff;
}

.card .card-block {
    padding: 1.25rem
}

.f-80 {
    font-size: 80px
}

.form-group {
    margin-bottom: 1.25em
}

.form-material .form-control {
    display: inline-block;
    height: 43px;
    width: 100%;
    border: none;
    border-radius: 0;
    font-size: 16px;
    font-weight: 400;
    padding: 9px;
    background-color: transparent;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-bottom: 1px solid #ccc
}

.btn-md {
    padding: 10px 16px;
    font-size: 15px;
    line-height: 23px
}

.btn-primary {
    background-color: #4099ff;
    border-color: #4099ff;
    color: #fff;
    cursor: pointer;
    -webkit-transition: all ease-in .3s;
    transition: all ease-in .3s
}

.btn {
    border-radius: 2px;
    text-transform: capitalize;
    font-size: 15px;
    padding: 10px 19px;
    cursor: pointer
}

.m-b-20 {
    margin-bottom: 20px
}

.btn-md {
    padding: 10px 16px;
    font-size: 15px;
    line-height: 23px
}

.heading{

  font-size: 21px;

}

#infoMessage p{

      color: red !important;
}


.btn-google {
        color: #545454;
    background-color: #ffffff;
    box-shadow: 0 1px 2px 1px #ddd;
}


.or-container {
    align-items: center;
    color: #ccc;
    display: flex;
    margin: 25px 0;
}

.line-separator {
    background-color: #ccc;
    flex-grow: 5;
    height: 1px;
}

.or-label {
    flex-grow: 1;
    margin: 0 15px;
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
				
						<section class="login-block">
						    <div class="container-fluid">
						        <div class="row">
						            <div class="col-sm-12">
						                
						                    <div class="auth-box card">
						                        <div class="card-block">
						                       
						                            <div class="row">
						                                <div class="col-md-12">
						                                    <h3 class="text-center heading" >歡迎 <span style="color:blue;">${sessionScope.admin.adminAccountName}</span> 先生/小姐!</h3>
						                                    
						                                </div>
						                                
						                                <table class="table">
						                                	<tr>
						                                		<th>管理員ID</th>
						                                		<td>${sessionScope.admin.adminId}</td>
						                                	</tr>
						                                	<tr>
						                                		<th>名稱</th>
						                                		<td>${sessionScope.admin.adminAccountName}</td>
						                                	</tr>
						                                	<tr>
						                                		<th>帳號</th>
						                                		<td>${sessionScope.admin.adminAccount}</td>
						                                	</tr>
						                                	<tr>
						                                		<th>狀態</th>
						                                		<td>${(sessionScope.admin.adminAccountStatus == 1)? '<span style="color:blue;">正常</span>' : '<span style="color:red;">停用</span>'}</td>
						                                	</tr>
						                                	<tr>
						                                		<th>權限項目</th>
						                                		<td>
						                                		<c:if test="${fn:contains(sessionScope.admin.adminAcess, '1')}">
																	[ 交流區 ]
																</c:if>
																<c:if test="${fn:contains(sessionScope.admin.adminAcess, '2')}">
																	[ 募資 ]  
																</c:if>
																<c:if test="${fn:contains(sessionScope.admin.adminAcess, '3')}">
																	[ 商城 ] 
																</c:if>
																<c:if test="${fn:contains(sessionScope.admin.adminAcess, '4')}">
																	[ 審核 ] 
																</c:if>
																<c:if test="${fn:contains(sessionScope.admin.adminAcess, '5')}">
																	[ 查看會員 ] 
																</c:if>
																<c:if test="${fn:contains(sessionScope.admin.adminAcess, '6')}">
																	[ 管理會員 ] 
																</c:if>
																<c:if test="${fn:contains(sessionScope.admin.adminAcess, '7')}">
																	[ 管理後台人員 ] 
																</c:if>
																</td>
						                                	</tr>
						                                </table>
						                                
						                            </div>
						                            <table class="table">
						                            	<tbody>
						                            		<tr>
						                            			
						                            		
						                            			<td align="left">
						                                    	<a href="<%=request.getContextPath()%>/back_end/admin/edit_personal_name.jsp">
						                                    	<input type="submit" class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20" name="submit" value="修改名稱">
						                            			</a>
						                            			</td>
						                            		
						                            			<td align="right">
						                            			<a href="<%=request.getContextPath()%>/back_end/admin/edit_personal_pw.jsp">
						                                    	<input type="submit" class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20" name="submit" value="修改密碼">
						                            			</a>
						                            			</td>
						                            		</tr>
						                            	</tbody>
						                            </table>        
						                        </div>
						                    </div>
						                
						            </div>
						        </div>
						    </div>
						</section>
				
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