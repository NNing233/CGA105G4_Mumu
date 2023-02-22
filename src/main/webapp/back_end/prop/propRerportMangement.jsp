<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.propreport.model.*"%>
<%@ page import="com.prop.model.PropService"%>
<%@ page import="com.prop.model.PropVO"%>
<%@ page import="com.prop.model.PropJDBCDAO"%>
<%@ page import="com.prop.model.PropDAO_interface"%>
<%@ page import="java.util.stream.Collectors"%>
<%
PropReportService propReportSvc = new PropReportService();
List<PropReportVO> list = propReportSvc.getAll();
pageContext.setAttribute("list", list);
List<PropReportVO> filteredList = list.stream().filter(propReportVO -> propReportVO.getReportStatus() == Status.審核中)
		.collect(Collectors.toList());
pageContext.setAttribute("filteredList", filteredList);
%>
<%
PropVO propVO = (PropVO) request.getAttribute("propVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<%
PropService propSvc = new PropService();
List<PropVO> listProp = propSvc.getAll();
pageContext.setAttribute("listProp", listProp);

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
<title>提案檢舉審核</title>
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
					<h1 class="mt-4">檢舉審核</h1>
					<form METHOD="post" ACTION="propReport.do" name="form"
						enctype="multipart/form-data">
						<ol class="breadcrumb mb-4">
						</ol>
						<table class="table table-hover ">

							<thead>
								<tr>

									<th scope="col-auto">提案檢舉編號</th>
									<th scope="col">檢舉人編號</th>
									<th scope="col">被檢舉提案</th>
									<th scope="col">檢舉時間</th>
									<th scope="col">檢舉內容</th>
									<th scope="col">回報結果</th>
									<th scope="col">審核結果</th>
								
								</tr>
							</thead>
							<tbody>
								<%@ include file="page3.file"%>
								<c:forEach var="propReportVO" items="${filteredList}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<th scope="row">${propReportVO.proposalReportId}</th>
										<td>${propReportVO.memberId}</td>
										<td>	
											<!-- Button to Open the Modal -->
											<button type="button" class="btn btn-primary"
												data-bs-toggle="modal"
												data-bs-target="#myModal_${propReportVO.proposalId}">詳細資料</button>

											<div class="modal" id="myModal_${propReportVO.proposalId}">
												<div class="modal-dialog modal-xl">
													<div class="modal-content">

														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">提案詳細資料</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>

														<!-- Modal body -->

														<div class="modal-body">
															<table class="table table-striped">
																<thead>
																	<tr>
																		<th>提案編號</th>
																	<th>提案名稱</th>
																	<th>目標募資金額</th>
																	
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<c:forEach var="propVO" items="${listProp}">
																		<c:if
																			test="${propReportVO.proposalId == propVO.proposalId}">
																			<tr>
																				<td>${propVO.proposalId}</td>
																				<td>${propVO.proposalName}</td>
																				<td>${propVO.targetDonateMoney}</td>
																			</tr>
																		</c:if>

																	</c:forEach>

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
										<td>${propReportVO.reportTime}</td>
										<td>${propReportVO.reportCause}</td>
										<td><div class="form-floating">
												<textarea type="TEXT" name="reportResult"
													class="form-control" placeholder="xxx"></textarea>

											</div></td>

									<td><input type="hidden" name="proposalReportId" value="${propReportVO.proposalReportId}">
										<input type="submit" class="btn btn-success"
											name="action" value="通過"> 
											<input type="submit"
											class="btn btn-danger" name="action" value="不通過"></td>
								</c:forEach>

							</tbody>
							

						</table>
					</form>
					<%@ include file="page4.file"%>
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