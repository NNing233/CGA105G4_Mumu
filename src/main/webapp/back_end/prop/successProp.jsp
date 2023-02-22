<%@page import="com.prop2.model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
PropService propSvc = new PropService();
List<PropVO> successPropList = propSvc.getSuccessProp();

pageContext.setAttribute("successPropList", successPropList);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<style>
.form-control {
	border-radius: 0.25rem !important;
	margin-left: 5px;
}

.t-heading {
	color: #727272;
}

.successPropItem td {
	color: #212121;
	font-weight: 400;
}

.btn-outline-primary {
	color: #007bff;
	background-color: transparent;
	background-image: none;
	border-color: #007bff;
	border-radius: 7px;
	font-size: 14px;
}

.btn-outline-primary:focus {
	color: #007bff;
}

.btn-outline-primary:hover {
	color: #fff;
}

.btn-outline-danger {
	color: #dc3545;
	background-color: transparent;
	background-image: none;
	border-color: #dc3545;
	font-size: 12px;
	border-radius: 7px;
}

.btn-outline-danger:hover {
	background-color: #dc3545;
	color: #fff !important;
}

.btn-outline-danger:focus {
	color: #dc3545;
}

.btn-primary {
	color: #fff;
	background-color: #007bff;
	border-color: #007bff;
	display: inline-block;
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	border: 1px solid transparent;
	padding: 0.375rem 1.5rem;
	font-size: 1rem;
	line-height: 1.5;
	border-radius: 0.25rem;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	height: 50px;
}
</style>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">
			<main>

				<!-- 請注意: 寫你要的內容 -->
				<!-- <h1>測試測試</h1> -->
				<div class="container-fluid px-4">

					<div class="record-table_wrapper">
						<h4 class="my-5 mb-0">已達標專案管理</h4>
						<table class="table table-hover" id="transactions">
							<thead>
								<tr role="row" class="t-heading">
									<th class="col-sm-1"></th>
									<th class="col-sm-3">已達標專案</th>
									<th class="col-sm-2">目標金額</th>
									<th class="col-sm-2">累積金額</th>
									<th class="col-sm-1">達成率(%)</th>

									<th class="col-sm-1">專案狀態</th>
									<th class="col-sm-1">聯絡人</th>
									<th class="col-sm-1">聯絡電話</th>
								</tr>
							</thead>
							<tbody class="recordlist">
								<c:forEach var="successProp" items="${successPropList }">
									<tr class="successPropItem">

										<td><img  style="max-width: 120px"
											src="<%=request.getContextPath()%>/prop/prop.do?action=showImg&proposalId=${successProp.proposalId}"
											alt="" class="popularImg img-fuid"
											onerror="this.src='<%=request.getContextPath()%>/front_end/assets/img/image_not_available.png'" /></td>
										<td><a
											href="<%=request.getContextPath() %>/front_end/prop/prop.jsp?prop=${successProp.proposalId}"
											target="_blank"
											style="color: #f8414a; font-size: 16px; font-weight: 400;">${successProp.proposalName }</a></td>

										<td>${successProp.targetDonateMoney }</td>
										<td>${successProp.accumulativeDonateMoney }
										</td>
										<td class="numberFont"
											style="font-weight: 500; font-size: 16px;">${100 * successProp.accumulativeDonateMoney/successProp.targetDonateMoney}%</td>
										<td style="font-size: 16px; font-weight: 600;  ${(successProp.proposalStatus>=2 && successProp.proposalStatus!=6)?'color:green':''} ${(successProp.proposalStatus==6)? 'color:red':''}">${ PropStatus.getStatusName(successProp.proposalStatus) }</td>

										<td style="font-size: 14px;">${successProp.memVO.memberName }</td>
										<td style="font-size: 14px;"><a href="tel:'${successProp.memVO.memberPhone }'">${successProp.memVO.memberPhone }</a></td>

									</tr>


									
								</c:forEach>
								<!-- survey Form -->


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
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"
		integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


	<c:if test="${param.change == 'success'}">
		<script>
			Swal.fire('更新成功', '', 'success');
		</script>
	</c:if>

	<c:if test="${param.add == 'success'}">
		<script>
			Swal.fire('新增分類成功', '', 'success');
		</script>
	</c:if>

	<script>
		$('tbody').on('click', '.editBtn', function(e) {
			e.preventDefault();
			console.log(123);
			let editTarget = $(this).prev();
			let value = editTarget.attr('contenteditable');

			if (value == 'false' || value == null) {
				editTarget.attr('contenteditable', 'true');
				editTarget.focus();
				editTarget.css("border", "solid 2px black");
			} else {
				editTarget.attr('contenteditable', 'false');
				editTarget.css("border", "");
				$(this).next().val(editTarget.text());
			}
		})
	</script>
</body>
</html>