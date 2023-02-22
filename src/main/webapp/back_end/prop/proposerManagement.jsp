<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prop.model.*"%>
<%@ page import="com.propopt.model.*"%>

<%
PropService propSvc = new PropService();
List<PropVO> list = propSvc.getAll();
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
<title>提案者資料</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
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
					<h1 class="mt-4">提案者資料管理</h1>
					<ol class="breadcrumb mb-4">

					</ol>




					<table class="table table-hover">

						<thead>
							<tr>

								<th scope="col">提案案號</th>
								<th scope="col">提案名稱</th>
								<th scope="col">提案者編號</th>
								<th scope="col">提案者姓名</th>
								<th scope="col">提案者詳細資料</th>
							</tr>
						</thead>

						<%@ include file="page1.file"%>
						<c:forEach var="propVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">

							<tbody>
								<tr>
									<th scope="row">${propVO.proposalId}</th>
									<td>${propVO.proposalName}</td>
									<td>${propVO.memberId}</td>
									<td>${propVO.memVO.memberName}</td>

									<td>
										<!-- Button to Open the Modal -->
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal"
											data-bs-target="#myModal_${propVO.proposalId}">詳細資料</button>

										<div class="modal" id="myModal_${propVO.proposalId}">
											<div class="modal-dialog modal-xl">
												<div class="modal-content">

													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title">提案者資料</h4>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal"></button>
													</div>

													<!-- Modal body -->

													<div class="modal-body">
														<table class="table table-striped">
															<thead>
																<tr>
																	<th>姓名</th>
																	<th>性別</th>
																	<th>註冊時間</th>
																	<th>會員狀態</th>
																	<th>身分證字號</th>
																	<th>出生年月日</th>
																	<th>信箱</th>
																	<th>手機</th>
																	<th>地址</th>
																	<th>銀行帳戶</th>
																	
																</tr>
															</thead>
															<tbody>
																<tr>

																	<td>${propVO.memVO.memberName}</td>
																	<td>${propVO.memVO.memberGender}</td>
																	<td>${propVO.memVO.signUpDatetime}</td>
																	<td>${propVO.memVO.memberStatus}</td>
																	<td>${propVO.memVO.idNumber}</td>
																	<td>${propVO.memVO.dateOfBirth}</td>
																	<td>${propVO.memVO.memberEmail}</td>
																	<td>${propVO.memVO.memberPhone}</td>
																	<td>${propVO.memVO.memberAddress}</td>
																	<td>${propVO.memVO.bankAccount}</td>

																</tr>
															</tbody>
														</table>
													</div>

													<!-- Modal footer -->
													<div class="modal-footer">
														<button type="button" class="btn btn-danger"
															data-bs-dismiss="modal">Close</button>
													</div>

												</div>
											</div>
										</div>


									</td>
								</tr>
							</tbody>






						</c:forEach>
						<%@ include file="page2.file"%>
					</table>









				</div>
		</div>
	</div>
	</main>
	<footer class="py-4 bg-light mt-auto">
		<div class="container-fluid px-4">
			<div class="d-flex align-items-center justify-content-between small">
				<div class="text-muted">Copyright &copy; Mumu 2022</div>
				<div>
					<a href="#">Privacy Policy</a> &middot; <a href="#">Terms &amp;
						Conditions</a>
				</div>
			</div>
		</div>
	</footer>
	</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="../js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="../js/datatables-simple-demo.js"></script>
</body>

</html>